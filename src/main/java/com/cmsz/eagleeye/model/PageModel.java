package com.cmsz.eagleeye.model;

import java.util.List;

import com.cmsz.eagleeye.constant.Constants;
import com.cmsz.eagleeye.util.ToolUtils;


public class PageModel<T> {

    /**
     * 当前页数
     */
    protected String currentPage;
    /**
     * 目标页数
     */
    protected String goPage;

    /**
     * 判断是否为cache中账户,true 是，false 不是
     */
    private boolean isCacheAccount;

    /**
     * @return the isCacheAccount
     */
    public boolean isCacheAccount() {
        return isCacheAccount;
    }

    /**
     * @param isCacheAccount the isCacheAccount to set
     */
    public void setCacheAccount(boolean isCacheAccount) {
        this.isCacheAccount = isCacheAccount;
    }

    /**
     * 目标页 最上一条在数据库下标
     */
    protected String goTopIndex;

    /**
     * @return the goTopIndex
     */
    public String getGoTopIndex() {
        return goTopIndex;
    }

    /**
     * @param goTopIndex the goTopIndex to set
     */
    public void setGoTopIndex(String goTopIndex) {
        if (!ToolUtils.isEmpty(this.pageSize) && !ToolUtils.isEmpty(this.goPage)) {
            int size = Integer.parseInt(this.getPageSize());
            int page = Integer.parseInt(this.goPage) - 1;
            goTopIndex = String.valueOf(size * page);
            this.goTopIndex = goTopIndex;
        } else {
            this.goTopIndex = goTopIndex;
        }
    }

    /**
     * @param goTopIndex the goTopIndex to set
     */
    public void initGoTopIndex() {
        if (!ToolUtils.isEmpty(this.pageSize) && !ToolUtils.isEmpty(this.goPage)) {
            final int size = Integer.parseInt(this.getPageSize());
            final int page =
                    Integer.parseInt(this.goPage)
                            - Integer.parseInt(Constants.DEFAULT_GO_PAGE);
            goTopIndex = String.valueOf(size * page);
        } else {
            this.pageSize = Constants.DEFAULT_PAGE_SIZE;
            this.goPage = Constants.DEFAULT_GO_PAGE;
            this.goTopIndex = Constants.DEFAULT_GO_TOP_INDEX;
        }
    }

    /**
     * 当前页 最上一条在数据库下标
     */
    protected String topIndex;
    /**
     * 当前页 最下一条在数据下标
     */
    protected String bottomIndex;

    /**
     * 总条数
     */
    protected String totalMsgCount;
    /**
     * 总页数
     */
    protected String totalPageCount;
    /**
     * 每页显示条数
     */
    protected String pageSize;

    /**
     * 首次分页时间
     * */
    protected long firstTime;

    /**
     * 分页中状态 暂停0 活动 1
     */
    protected String nowStatus;

    /**
     * 存放查询结果集
     */
    protected List<T> resultList;


    public String getNowStatus() {
        return nowStatus;
    }

    public void setNowStatus(String nowStatus) {
        this.nowStatus = nowStatus;
    }

    public String getTotalMsgCount() {
        return totalMsgCount;
    }

    public void setTotalMsgCount(String totalMsgCount) {
        this.totalMsgCount = totalMsgCount;
    }

    public String getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        if (ToolUtils.isEmpty(pageSize)) {
            pageSize = "10";
        } else {
            this.pageSize = pageSize;
        }

    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getGoPage() {
        return goPage;
    }

    public void setGoPage(String goPage) {
        if (ToolUtils.isEmpty(goPage)) {
            goPage = "1";
        }
        this.goPage = goPage;
    }

    public String getTopIndex() {
        return topIndex;
    }

    public void setTopIndex(String topIndex) {
        this.topIndex = topIndex;
    }

    public String getBottomIndex() {
        return bottomIndex;
    }

    public void setBottomIndex(String bottomIndex) {
        this.bottomIndex = bottomIndex;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    protected String isNew;

    public long getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(long firstTime) {
        this.firstTime = firstTime;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }


}
