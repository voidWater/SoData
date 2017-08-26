package org.soData.pojo;

public class Rule {
	/** 
     * url
     */  
    private String url;  
  
    /** 
     * 参数名
     */  
    private String[] params;  
    /** 
     * 参数值 
     */  
    private String[] values;  
  
    /** 
     * 返回类型
     */  
    private String selectModel;  
  
    /** 
     * CLASS / ID / SELECTION 
     * 筛选方式  
     */  
    private int type = ID ;  
      
	/** 
     *GET / POST 
     * 请求方法
     */  
    private int requestMoethod = GET ;   
    
    /**
     *请求相应时间 
     *TIME_OUT
     */
    public int time_out = TIME_OUT;
    
    public final static int TIME_OUT=3000;
    
    public final static int GET = 0 ;  
    public final static int POST = 1 ;  
      
  
    public final static int CLASS = 0;  
    public final static int ID = 1;  
    public final static int SELECTION = 2;
    public Rule(){}
    
    public Rule(String url, String[] params, String[] values,
			String selectModel, int type, int requestMoethod, int time_out) {
		super();
		this.url = url;
		this.params = params;
		this.values = values;
		this.selectModel = selectModel;
		this.type = type;
		this.requestMoethod = requestMoethod;
		this.time_out = time_out;
	}

	public int getTime_out() {
		return time_out;
	}
	public void setTime_out(int time_out) {
		this.time_out = time_out;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public String getSelectModel() {
		return selectModel;
	}
	public void setSelectModel(String selectModel) {
		this.selectModel = selectModel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRequestMoethod() {
		return requestMoethod;
	}
	public void setRequestMoethod(int requestMoethod) {
		this.requestMoethod = requestMoethod;
	}
}
