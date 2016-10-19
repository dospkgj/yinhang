package yinhang.sql;

public enum DBEnum {
	机构理财("jigoulicai"), 客户经理("kehu"), 营销("yingxiao"), 小企业("zhongxiao"), 会计上岗(
			"kuaijishanggang"), 信贷业务("xindaiyewu");
	private String dbName;

	DBEnum(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
