package com.kenshi.gallery.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kenshi.gallery.data.model.PhotoResponse
import com.kenshi.gallery.data.service.GalleryService
import com.kenshi.gallery.data.util.Constants.PAGING_SIZE
import com.kenshi.gallery.data.util.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class GalleryPagingSource(
    private val service: GalleryService,
) : PagingSource<Int, PhotoResponse>() {

    // PagingDataAdapter.refresh() or LazyPagingItems.refresh() 와 같은 함수를 통해 수동으로 리프레시를 할 때 호출됨
    override fun getRefreshKey(state: PagingState<Int, PhotoResponse>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        Timber.d("getRefreshKey 호출")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = service.getPhotoList(pageNumber, limit = params.loadSize)

            val endOfPaginationReached = response.isEmpty()

            LoadResult.Page(
                data = response,
                prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1,
                nextKey = if (endOfPaginationReached) {
                    null
                } else {
                    // initial load size = 3 * NETWORK_PAGE_SIZE
                    // ensure we're not requesting duplicating items, at the 2nd request
                    pageNumber + (params.loadSize / PAGING_SIZE)
                },
            )
        } catch (exception: IOException) {
            Timber.e(exception)
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}
