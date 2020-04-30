package cn.entity;

import java.util.List;

public class Page {

    //每页的数据
    private List<Book> list;

    //总记录数
    private long totalRecord;

    //每页显式记录数，暂规定每页显示3条
    private int pageSize = 3;

    //总页数
    private int totalPageCount;

    //当前显示页
    private int currentPageCount;

    //显示数据开始读取的位置
    private int startIndex;

    //显示的开始页和结束页
    private int startPage;
    private int endPage;

    private String url;


    public Page() {
    }

    public Page(int currentPageCount, long totalRecord) {
    	
        this.currentPageCount = currentPageCount;

        totalPageCount = (int) (totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1);

        this.totalRecord = totalRecord;

        //开始读取数据位置
        startIndex = (currentPageCount - 1) * pageSize;

        //如果当前页小于10，那么开始页为1，结束为6（总显示6页）
        if (this.currentPageCount <= 6) {
            this.startPage = 1;
            this.endPage = totalPageCount;
        } else {
            this.startPage = this.currentPageCount - 2;
            this.endPage = this.totalPageCount + 3;

            //加减后页数越界
            if (startPage < 1) {
                this.startPage = 1;
                this.endPage = totalPageCount;
            }
            if (endPage > totalPageCount) {
                this.startPage = this.currentPageCount - 5;
                this.endPage = this.totalPageCount;
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setCurrentPageCount(int currentPageCount) {
        this.currentPageCount = currentPageCount;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public long getCurrentPageCount() {
        return currentPageCount;
    }

    public void setCurrentPageCount(long currentPageCount) {
        this.currentPageCount = (int) currentPageCount;
    }
}