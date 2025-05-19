# buession-web 参考手册


## Response


我们定义了 restful 返回数据类型中的分页对象。

```java
public class Pagination {

	/**
	 * 默认每页大小
	 */
	public final static int PAGESIZE = 15;

	/**
	 * 当前页码
	 */
	private int page = 1;

	/**
	 * 每页大小
	 */
	private int pagesize = PAGESIZE;

	/**
	 * 前一页页码
	 */
	private int previousPage = 1;

	/**
	 * 下一页页码
	 */
	private int nextPage = 1;

	/**
	 * 总页码
	 */
	private int totalPages = 1;

	/**
	 * 总记录数
	 */
	private long totalRecords = 0;

	/**
	 * Constructs with default configuration.
	 */
	public Pagination();

	/**
	 * Constructs with page and pagesize.
	 *
	 * @param page
	 * 		当前页码
	 * @param pagesize
	 * 		每页大小
	 */
	public Pagination(int page, int pagesize);

	/**
	 * Constructs with page, pagesize and totalRecords.
	 *
	 * @param page
	 * 		当前页码
	 * @param pagesize
	 * 		每页大小
	 * @param totalRecords
	 * 		总记录数
	 */
	public Pagination(int page, int pagesize, long totalRecords);

	/**
	 * 返回当前页码
	 *
	 * @return 当前页码
	 */
	public int getPage();

	/**
	 * 设置当前页码
	 *
	 * @param page
	 * 		当前页码
	 */
	public void setPage(int page);

	/**
	 * 返回每页大小
	 *
	 * @return 每页大小
	 */
	public int getPagesize();

	/**
	 * 设置每页大小
	 *
	 * @param pagesize
	 * 		每页大小
	 */
	public void setPagesize(int pagesize);

	/**
	 * 返回前一页页码
	 *
	 * @return 前一页页码
	 */
	public int getPreviousPage();

	/**
	 * 设置前一页页码
	 *
	 * @param previousPage
	 * 		前一页页码
	 */
	public void setPreviousPage(int previousPage);

	/**
	 * 返回下一页页码
	 *
	 * @return 下一页页码
	 */
	public int getNextPage(){
		return nextPage;
	}

	/**
	 * 设置下一页页码
	 *
	 * @param nextPage
	 * 		下一页页码
	 */
	public void setNextPage(int nextPage);

	/**
	 * 返回总页码
	 *
	 * @return 总页码
	 */
	public int getTotalPages();

	/**
	 * 设置总页码
	 *
	 * @param totalPages
	 * 		总页码
	 */
	public void setTotalPages(int totalPages);

	/**
	 * 返回总记录数
	 *
	 * @return 总记录数
	 */
	public long getTotalRecords();

	/**
	 * 设置总记录数
	 *
	 * @param totalRecords
	 * 		总记录数
	 */
	public void setTotalRecords(long totalRecords);

}
```