package cn.entity;

import java.util.List;

public class Page {

    //ÿҳ������
    private List<Book> list;

    //�ܼ�¼��
    private long totalRecord;

    //ÿҳ��ʽ��¼�����ݹ涨ÿҳ��ʾ3��
    private int pageSize = 3;

    //��ҳ��
    private int totalPageCount;

    //��ǰ��ʾҳ
    private int currentPageCount;

    //��ʾ���ݿ�ʼ��ȡ��λ��
    private int startIndex;

    //��ʾ�Ŀ�ʼҳ�ͽ���ҳ
    private int startPage;
    private int endPage;

    private String url;


    public Page() {
    }

    public Page(int currentPageCount, long totalRecord) {
    	
        this.currentPageCount = currentPageCount;

        totalPageCount = (int) (totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1);

        this.totalRecord = totalRecord;

        //��ʼ��ȡ����λ��
        startIndex = (currentPageCount - 1) * pageSize;

        //�����ǰҳС��10����ô��ʼҳΪ1������Ϊ6������ʾ6ҳ��
        if (this.currentPageCount <= 6) {
            this.startPage = 1;
            this.endPage = totalPageCount;
        } else {
            this.startPage = this.currentPageCount - 2;
            this.endPage = this.totalPageCount + 3;

            //�Ӽ���ҳ��Խ��
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