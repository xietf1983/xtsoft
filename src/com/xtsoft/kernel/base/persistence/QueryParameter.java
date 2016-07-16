package com.xtsoft.kernel.base.persistence;

public class QueryParameter {

	private long maxResults = Long.MAX_VALUE;
	private long firstResult = 0;
	private Object parameter;

	public QueryParameter() {

	}

	public QueryParameter(Object parameter, int firstResult, int maxResults) {
		this.parameter = parameter;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	public long getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(long firstResult) {
		this.firstResult = firstResult;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

}
