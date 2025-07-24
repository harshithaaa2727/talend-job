
package harshithabowreshetty.job_0_1;

import routines.HashUtil;
import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.MDM;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 





@SuppressWarnings("unused")

/**
 * Job: job Purpose: <br>
 * Description:  <br>
 * @author Bowreshetty, Harshitha
 * @version 8.0.1.20250625_0954-patch
 * @status 
 */
public class job implements TalendJob {
	static {System.setProperty("TalendJob.log", "job.log");}

	

	
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(job.class);
	

static {
         String javaUtilLoggingConfigFile = System.getProperty("java.util.logging.config.file");
         if (javaUtilLoggingConfigFile == null) {
             setupDefaultJavaUtilLogging();
         }
}

/**
* This class replaces the default {@code System.err} stream used by Java Util Logging (JUL).
* You can use your own configuration through the
* {@code java.util.logging.config.file} system property, enabling you to specify an external
* logging configuration file for tailored logging setup.
*/
public static class StandardConsoleHandler extends java.util.logging.StreamHandler {
     public StandardConsoleHandler() {
         // Set System.out as default log output stream
         super(System.out, new java.util.logging.SimpleFormatter());
     }

     /**
      * Publish a {@code LogRecord}.
      * The logging request was made initially to a {@code Logger} object,
      * which initialized the {@code LogRecord} and forwarded it here.
      *
      * @param  record  description of the log event. A null record is
      *                 silently ignored and is not published
      */
      @Override
      public void publish(java.util.logging.LogRecord record) {
            super.publish(record);
            flush();
      }

      /**
      * Override {@code StreamHandler.close} to do a flush but not
      * to close the output stream.  That is, we do <b>not</b>
      * close {@code System.out}.
      */
      @Override
      public void close() {
            flush();
      }
}


protected static void setupDefaultJavaUtilLogging() {
      java.util.logging.LogManager logManager = java.util.logging.LogManager.getLogManager();

      // Get the root logger
      java.util.logging.Logger rootLogger = logManager.getLogger("");

      // Remove existing handlers to set standard console handler only
      java.util.logging.Handler[] handlers = rootLogger.getHandlers();
      for (java.util.logging.Handler handler : handlers) {
            rootLogger.removeHandler(handler);
      }

      rootLogger.addHandler(new StandardConsoleHandler());
      rootLogger.setLevel(java.util.logging.Level.INFO);
}

protected static boolean isCBPClientPresent() {
    boolean isCBPClientPresent = false;
    try {
         Class.forName("org.talend.metrics.CBPClient");
         isCBPClientPresent = true;
        } catch (java.lang.ClassNotFoundException e) {
    }
    return isCBPClientPresent;
}

protected static void logIgnoredError(String message, Throwable cause) {
       log.error(message, cause);

}


	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";

	public static String taskExecutionId = null;

	public static String jobExecutionId = java.util.UUID.randomUUID().toString();;

	private final static boolean isCBPClientPresent = isCBPClientPresent();	

	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
		

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
		}
		
		//if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if(NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}
			
	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.
	public ContextProperties getContext() {
		return this.context;
	}

	protected java.util.Map<String, String> defaultProperties = new java.util.HashMap<String, String>();
	protected java.util.Map<String, String> additionalProperties = new java.util.HashMap<String, String>();

	public java.util.Map<String, String> getDefaultProperties() {
	    return this.defaultProperties;
	}

	public java.util.Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

	private final String jobVersion = "0.1";
	private final String jobName = "job";
	private final String projectName = "HARSHITHABOWRESHETTY";
	public Integer errorCode = null;
	private String currentComponent = "";
	public static boolean isStandaloneMS = Boolean.valueOf("false");
	
	private void s(final String component) {
		try {
			org.talend.metrics.DataReadTracker.setCurrentComponent(jobName, component);
		} catch (Exception | NoClassDefFoundError e) {
			// ignore
		}
	}
	
	
	private void mdc(final String subJobName, final String subJobPidPrefix) {
		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", subJobName);
		org.slf4j.MDC.put("_subJobPid", subJobPidPrefix + subJobPidCounter.getAndIncrement());
	}
	
	
	private void sh(final String componentId) {
		ok_Hash.put(componentId, false);
		start_Hash.put(componentId, System.currentTimeMillis());
	}

	{
	s("none");
	}

	
	private String cLabel =  null;
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName, "_1_4oUGhHEfCbwrwNwoBUXQ", "0.1");
private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";
	
	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}
	
	public void setDataSourceReferences(List serviceReferences) throws Exception{
		
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();
		
		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils.getServices(serviceReferences,  javax.sql.DataSource.class).entrySet()) {
                    dataSources.put(entry.getKey(), entry.getValue());
                    talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	
	private String currentComponent = null;
	private String cLabel =  null;
	
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}
	
	private TalendException(Exception e, String errorComponent, String errorComponentLabel, final java.util.Map<String, Object> globalMap) {
		this(e, errorComponent, globalMap);
		this.cLabel = errorComponentLabel;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
			}
		}
		if (!(e instanceof TalendException)) {
			job.this.exception = e;
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(job.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
		if(enableLogStash) {
			talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
			talendJobLogProcess(globalMap);
		}
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tFileInputDelimited_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void cdcHashJoblet_1_tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tLogRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tLogRow_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tLogRow_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileInputDelimited_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void cdcHashJoblet_2_tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void talendJobLog_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					talendJobLog_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void talendJobLog_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	



public static class InsertRecordsStruct implements routines.system.IPersistableRow<InsertRecordsStruct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				
			    public String hash_code;

				public String getHash_code () {
					return this.hash_code;
				}

				public Boolean hash_codeIsNullable(){
				    return true;
				}
				public Boolean hash_codeIsKey(){
				    return false;
				}
				public Integer hash_codeLength(){
				    return null;
				}
				public Integer hash_codePrecision(){
				    return null;
				}
				public String hash_codeDefault(){
				
					return null;
				
				}
				public String hash_codeComment(){
				
				    return "";
				
				}
				public String hash_codePattern(){
				
					return "";
				
				}
				public String hash_codeOriginalDbColumnName(){
				
					return "hash_code";
				
				}

				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.cust_id == null) ? 0 : this.cust_id.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final InsertRecordsStruct other = (InsertRecordsStruct) obj;
		
						if (this.cust_id == null) {
							if (other.cust_id != null)
								return false;
						
						} else if (!this.cust_id.equals(other.cust_id))
						
							return false;
					

		return true;
    }

	public void copyDataTo(InsertRecordsStruct other) {

		other.cust_id = this.cust_id;
	            other.first_name = this.first_name;
	            other.last_name = this.last_name;
	            other.email = this.email;
	            other.hash_code = this.hash_code;
	            
	}

	public void copyKeysDataTo(InsertRecordsStruct other) {

		other.cust_id = this.cust_id;
	            	
	}



	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
		sb.append(",hash_code="+hash_code);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        				if(hash_code == null){
        					sb.append("<null>");
        				}else{
            				sb.append(hash_code);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(InsertRecordsStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.cust_id, other.cust_id);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class UpdateRecordsStruct implements routines.system.IPersistableRow<UpdateRecordsStruct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				
			    public String hash_code;

				public String getHash_code () {
					return this.hash_code;
				}

				public Boolean hash_codeIsNullable(){
				    return true;
				}
				public Boolean hash_codeIsKey(){
				    return false;
				}
				public Integer hash_codeLength(){
				    return null;
				}
				public Integer hash_codePrecision(){
				    return null;
				}
				public String hash_codeDefault(){
				
					return null;
				
				}
				public String hash_codeComment(){
				
				    return "";
				
				}
				public String hash_codePattern(){
				
					return "";
				
				}
				public String hash_codeOriginalDbColumnName(){
				
					return "hash_code";
				
				}

				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.cust_id == null) ? 0 : this.cust_id.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final UpdateRecordsStruct other = (UpdateRecordsStruct) obj;
		
						if (this.cust_id == null) {
							if (other.cust_id != null)
								return false;
						
						} else if (!this.cust_id.equals(other.cust_id))
						
							return false;
					

		return true;
    }

	public void copyDataTo(UpdateRecordsStruct other) {

		other.cust_id = this.cust_id;
	            other.first_name = this.first_name;
	            other.last_name = this.last_name;
	            other.email = this.email;
	            other.hash_code = this.hash_code;
	            
	}

	public void copyKeysDataTo(UpdateRecordsStruct other) {

		other.cust_id = this.cust_id;
	            	
	}



	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
		sb.append(",hash_code="+hash_code);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        				if(hash_code == null){
        					sb.append("<null>");
        				}else{
            				sb.append(hash_code);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(UpdateRecordsStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.cust_id, other.cust_id);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class NoChangeRecordsStruct implements routines.system.IPersistableRow<NoChangeRecordsStruct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				
			    public String hash_code;

				public String getHash_code () {
					return this.hash_code;
				}

				public Boolean hash_codeIsNullable(){
				    return true;
				}
				public Boolean hash_codeIsKey(){
				    return false;
				}
				public Integer hash_codeLength(){
				    return null;
				}
				public Integer hash_codePrecision(){
				    return null;
				}
				public String hash_codeDefault(){
				
					return null;
				
				}
				public String hash_codeComment(){
				
				    return "";
				
				}
				public String hash_codePattern(){
				
					return "";
				
				}
				public String hash_codeOriginalDbColumnName(){
				
					return "hash_code";
				
				}

				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.cust_id == null) ? 0 : this.cust_id.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final NoChangeRecordsStruct other = (NoChangeRecordsStruct) obj;
		
						if (this.cust_id == null) {
							if (other.cust_id != null)
								return false;
						
						} else if (!this.cust_id.equals(other.cust_id))
						
							return false;
					

		return true;
    }

	public void copyDataTo(NoChangeRecordsStruct other) {

		other.cust_id = this.cust_id;
	            other.first_name = this.first_name;
	            other.last_name = this.last_name;
	            other.email = this.email;
	            other.hash_code = this.hash_code;
	            
	}

	public void copyKeysDataTo(NoChangeRecordsStruct other) {

		other.cust_id = this.cust_id;
	            	
	}



	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
		sb.append(",hash_code="+hash_code);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        				if(hash_code == null){
        					sb.append("<null>");
        				}else{
            				sb.append(hash_code);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(NoChangeRecordsStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.cust_id, other.cust_id);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];

	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				
			    public String hash_code;

				public String getHash_code () {
					return this.hash_code;
				}

				public Boolean hash_codeIsNullable(){
				    return true;
				}
				public Boolean hash_codeIsKey(){
				    return false;
				}
				public Integer hash_codeLength(){
				    return null;
				}
				public Integer hash_codePrecision(){
				    return null;
				}
				public String hash_codeDefault(){
				
					return null;
				
				}
				public String hash_codeComment(){
				
				    return "";
				
				}
				public String hash_codePattern(){
				
					return "";
				
				}
				public String hash_codeOriginalDbColumnName(){
				
					return "hash_code";
				
				}

				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
					this.hash_code = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.hash_code,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
		sb.append(",hash_code="+hash_code);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        				if(hash_code == null){
        					sb.append("<null>");
        				}else{
            				sb.append(hash_code);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];

	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class after_tFileInputDelimited_1Struct implements routines.system.IPersistableRow<after_tFileInputDelimited_1Struct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.cust_id == null) ? 0 : this.cust_id.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final after_tFileInputDelimited_1Struct other = (after_tFileInputDelimited_1Struct) obj;
		
						if (this.cust_id == null) {
							if (other.cust_id != null)
								return false;
						
						} else if (!this.cust_id.equals(other.cust_id))
						
							return false;
					

		return true;
    }

	public void copyDataTo(after_tFileInputDelimited_1Struct other) {

		other.cust_id = this.cust_id;
	            other.first_name = this.first_name;
	            other.last_name = this.last_name;
	            other.email = this.email;
	            
	}

	public void copyKeysDataTo(after_tFileInputDelimited_1Struct other) {

		other.cust_id = this.cust_id;
	            	
	}



	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(after_tFileInputDelimited_1Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.cust_id, other.cust_id);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

	final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_1", "t3qz72_");

	
		String iterateId = "";
	
	
	String currentComponent = "";
	s("none");
	String cLabel =  null;
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;


		tFileInputDelimited_2Process(globalMap);

		row1Struct row1 = new row1Struct();
row3Struct row3 = new row3Struct();
InsertRecordsStruct InsertRecords = new InsertRecordsStruct();
UpdateRecordsStruct UpdateRecords = new UpdateRecordsStruct();
NoChangeRecordsStruct NoChangeRecords = new NoChangeRecordsStruct();






	
	/**
	 * [tLogRow_1 begin ] start
	 */

	

	
		
		sh("tLogRow_1");
		
	
	s(currentComponent="tLogRow_1");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"InsertRecords");
			
		int tos_count_tLogRow_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tLogRow_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tLogRow_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
                    log4jParamters_tLogRow_1.append("Parameters:");
                            log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "false");
                        log4jParamters_tLogRow_1.append(" | ");
                            log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "true");
                        log4jParamters_tLogRow_1.append(" | ");
                            log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
                        log4jParamters_tLogRow_1.append(" | ");
                            log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
                        log4jParamters_tLogRow_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tLogRow_1 - "  + (log4jParamters_tLogRow_1) );
                    } 
                } 
            new BytesLimit65535_tLogRow_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tLogRow_1", "tLogRow_1", "tLogRow");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

	///////////////////////
	
         class Util_tLogRow_1 {

        String[] des_top = { ".", ".", "-", "+" };

        String[] des_head = { "|=", "=|", "-", "+" };

        String[] des_bottom = { "'", "'", "-", "+" };

        String name="";

        java.util.List<String[]> list = new java.util.ArrayList<String[]>();

        int[] colLengths = new int[5];

        public void addRow(String[] row) {

            for (int i = 0; i < 5; i++) {
                if (row[i]!=null) {
                  colLengths[i] = Math.max(colLengths[i], row[i].length());
                }
            }
            list.add(row);
        }

        public void setTableName(String name) {

            this.name = name;
        }

            public StringBuilder format() {
            
                StringBuilder sb = new StringBuilder();
  
            
                    sb.append(print(des_top));
    
                    int totals = 0;
                    for (int i = 0; i < colLengths.length; i++) {
                        totals = totals + colLengths[i];
                    }
    
                    // name
                    sb.append("|");
                    int k = 0;
                    for (k = 0; k < (totals + 4 - name.length()) / 2; k++) {
                        sb.append(' ');
                    }
                    sb.append(name);
                    for (int i = 0; i < totals + 4 - name.length() - k; i++) {
                        sb.append(' ');
                    }
                    sb.append("|\n");

                    // head and rows
                    sb.append(print(des_head));
                    for (int i = 0; i < list.size(); i++) {
    
                        String[] row = list.get(i);
    
                        java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());
                        
                        StringBuilder sbformat = new StringBuilder();                                             
        			        sbformat.append("|%1$-");
        			        sbformat.append(colLengths[0]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%2$-");
        			        sbformat.append(colLengths[1]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%3$-");
        			        sbformat.append(colLengths[2]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%4$-");
        			        sbformat.append(colLengths[3]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%5$-");
        			        sbformat.append(colLengths[4]);
        			        sbformat.append("s");
        			                      
                        sbformat.append("|\n");                    
       
                        formatter.format(sbformat.toString(), (Object[])row);	
                                
                        sb.append(formatter.toString());
                        if (i == 0)
                            sb.append(print(des_head)); // print the head
                    }
    
                    // end
                    sb.append(print(des_bottom));
                    return sb;
                }
            

            private StringBuilder print(String[] fillChars) {
                StringBuilder sb = new StringBuilder();
                //first column
                sb.append(fillChars[0]);                
                    for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);	                

                    for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                
                    //last column
                    for (int i = 0; i < colLengths[4] - fillChars[1].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }         
                sb.append(fillChars[1]);
                sb.append("\n");               
                return sb;
            }
            
            public boolean isTableEmpty(){
            	if (list.size() > 1)
            		return false;
            	return true;
            }
        }
        Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
        util_tLogRow_1.setTableName("tLogRow_1");
        util_tLogRow_1.addRow(new String[]{"cust_id","first_name","last_name","email","hash_code",});        
 		StringBuilder strBuffer_tLogRow_1 = null;
		int nb_line_tLogRow_1 = 0;
///////////////////////    			



 



		

/**
 * [tLogRow_1 begin ] stop
 */





	
	/**
	 * [tLogRow_2 begin ] start
	 */

	

	
		
		sh("tLogRow_2");
		
	
	s(currentComponent="tLogRow_2");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"UpdateRecords");
			
		int tos_count_tLogRow_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tLogRow_2 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tLogRow_2{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tLogRow_2 = new StringBuilder();
                    log4jParamters_tLogRow_2.append("Parameters:");
                            log4jParamters_tLogRow_2.append("BASIC_MODE" + " = " + "false");
                        log4jParamters_tLogRow_2.append(" | ");
                            log4jParamters_tLogRow_2.append("TABLE_PRINT" + " = " + "true");
                        log4jParamters_tLogRow_2.append(" | ");
                            log4jParamters_tLogRow_2.append("VERTICAL" + " = " + "false");
                        log4jParamters_tLogRow_2.append(" | ");
                            log4jParamters_tLogRow_2.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
                        log4jParamters_tLogRow_2.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tLogRow_2 - "  + (log4jParamters_tLogRow_2) );
                    } 
                } 
            new BytesLimit65535_tLogRow_2().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tLogRow_2", "tLogRow_2", "tLogRow");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

	///////////////////////
	
         class Util_tLogRow_2 {

        String[] des_top = { ".", ".", "-", "+" };

        String[] des_head = { "|=", "=|", "-", "+" };

        String[] des_bottom = { "'", "'", "-", "+" };

        String name="";

        java.util.List<String[]> list = new java.util.ArrayList<String[]>();

        int[] colLengths = new int[5];

        public void addRow(String[] row) {

            for (int i = 0; i < 5; i++) {
                if (row[i]!=null) {
                  colLengths[i] = Math.max(colLengths[i], row[i].length());
                }
            }
            list.add(row);
        }

        public void setTableName(String name) {

            this.name = name;
        }

            public StringBuilder format() {
            
                StringBuilder sb = new StringBuilder();
  
            
                    sb.append(print(des_top));
    
                    int totals = 0;
                    for (int i = 0; i < colLengths.length; i++) {
                        totals = totals + colLengths[i];
                    }
    
                    // name
                    sb.append("|");
                    int k = 0;
                    for (k = 0; k < (totals + 4 - name.length()) / 2; k++) {
                        sb.append(' ');
                    }
                    sb.append(name);
                    for (int i = 0; i < totals + 4 - name.length() - k; i++) {
                        sb.append(' ');
                    }
                    sb.append("|\n");

                    // head and rows
                    sb.append(print(des_head));
                    for (int i = 0; i < list.size(); i++) {
    
                        String[] row = list.get(i);
    
                        java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());
                        
                        StringBuilder sbformat = new StringBuilder();                                             
        			        sbformat.append("|%1$-");
        			        sbformat.append(colLengths[0]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%2$-");
        			        sbformat.append(colLengths[1]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%3$-");
        			        sbformat.append(colLengths[2]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%4$-");
        			        sbformat.append(colLengths[3]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%5$-");
        			        sbformat.append(colLengths[4]);
        			        sbformat.append("s");
        			                      
                        sbformat.append("|\n");                    
       
                        formatter.format(sbformat.toString(), (Object[])row);	
                                
                        sb.append(formatter.toString());
                        if (i == 0)
                            sb.append(print(des_head)); // print the head
                    }
    
                    // end
                    sb.append(print(des_bottom));
                    return sb;
                }
            

            private StringBuilder print(String[] fillChars) {
                StringBuilder sb = new StringBuilder();
                //first column
                sb.append(fillChars[0]);                
                    for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);	                

                    for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                
                    //last column
                    for (int i = 0; i < colLengths[4] - fillChars[1].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }         
                sb.append(fillChars[1]);
                sb.append("\n");               
                return sb;
            }
            
            public boolean isTableEmpty(){
            	if (list.size() > 1)
            		return false;
            	return true;
            }
        }
        Util_tLogRow_2 util_tLogRow_2 = new Util_tLogRow_2();
        util_tLogRow_2.setTableName("tLogRow_2");
        util_tLogRow_2.addRow(new String[]{"cust_id","first_name","last_name","email","hash_code",});        
 		StringBuilder strBuffer_tLogRow_2 = null;
		int nb_line_tLogRow_2 = 0;
///////////////////////    			



 



		

/**
 * [tLogRow_2 begin ] stop
 */





	
	/**
	 * [tLogRow_3 begin ] start
	 */

	

	
		
		sh("tLogRow_3");
		
	
	s(currentComponent="tLogRow_3");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"NoChangeRecords");
			
		int tos_count_tLogRow_3 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tLogRow_3 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tLogRow_3{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tLogRow_3 = new StringBuilder();
                    log4jParamters_tLogRow_3.append("Parameters:");
                            log4jParamters_tLogRow_3.append("BASIC_MODE" + " = " + "false");
                        log4jParamters_tLogRow_3.append(" | ");
                            log4jParamters_tLogRow_3.append("TABLE_PRINT" + " = " + "true");
                        log4jParamters_tLogRow_3.append(" | ");
                            log4jParamters_tLogRow_3.append("VERTICAL" + " = " + "false");
                        log4jParamters_tLogRow_3.append(" | ");
                            log4jParamters_tLogRow_3.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
                        log4jParamters_tLogRow_3.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tLogRow_3 - "  + (log4jParamters_tLogRow_3) );
                    } 
                } 
            new BytesLimit65535_tLogRow_3().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tLogRow_3", "tLogRow_3", "tLogRow");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

	///////////////////////
	
         class Util_tLogRow_3 {

        String[] des_top = { ".", ".", "-", "+" };

        String[] des_head = { "|=", "=|", "-", "+" };

        String[] des_bottom = { "'", "'", "-", "+" };

        String name="";

        java.util.List<String[]> list = new java.util.ArrayList<String[]>();

        int[] colLengths = new int[5];

        public void addRow(String[] row) {

            for (int i = 0; i < 5; i++) {
                if (row[i]!=null) {
                  colLengths[i] = Math.max(colLengths[i], row[i].length());
                }
            }
            list.add(row);
        }

        public void setTableName(String name) {

            this.name = name;
        }

            public StringBuilder format() {
            
                StringBuilder sb = new StringBuilder();
  
            
                    sb.append(print(des_top));
    
                    int totals = 0;
                    for (int i = 0; i < colLengths.length; i++) {
                        totals = totals + colLengths[i];
                    }
    
                    // name
                    sb.append("|");
                    int k = 0;
                    for (k = 0; k < (totals + 4 - name.length()) / 2; k++) {
                        sb.append(' ');
                    }
                    sb.append(name);
                    for (int i = 0; i < totals + 4 - name.length() - k; i++) {
                        sb.append(' ');
                    }
                    sb.append("|\n");

                    // head and rows
                    sb.append(print(des_head));
                    for (int i = 0; i < list.size(); i++) {
    
                        String[] row = list.get(i);
    
                        java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());
                        
                        StringBuilder sbformat = new StringBuilder();                                             
        			        sbformat.append("|%1$-");
        			        sbformat.append(colLengths[0]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%2$-");
        			        sbformat.append(colLengths[1]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%3$-");
        			        sbformat.append(colLengths[2]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%4$-");
        			        sbformat.append(colLengths[3]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%5$-");
        			        sbformat.append(colLengths[4]);
        			        sbformat.append("s");
        			                      
                        sbformat.append("|\n");                    
       
                        formatter.format(sbformat.toString(), (Object[])row);	
                                
                        sb.append(formatter.toString());
                        if (i == 0)
                            sb.append(print(des_head)); // print the head
                    }
    
                    // end
                    sb.append(print(des_bottom));
                    return sb;
                }
            

            private StringBuilder print(String[] fillChars) {
                StringBuilder sb = new StringBuilder();
                //first column
                sb.append(fillChars[0]);                
                    for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);	                

                    for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                
                    //last column
                    for (int i = 0; i < colLengths[4] - fillChars[1].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }         
                sb.append(fillChars[1]);
                sb.append("\n");               
                return sb;
            }
            
            public boolean isTableEmpty(){
            	if (list.size() > 1)
            		return false;
            	return true;
            }
        }
        Util_tLogRow_3 util_tLogRow_3 = new Util_tLogRow_3();
        util_tLogRow_3.setTableName("tLogRow_3");
        util_tLogRow_3.addRow(new String[]{"cust_id","first_name","last_name","email","hash_code",});        
 		StringBuilder strBuffer_tLogRow_3 = null;
		int nb_line_tLogRow_3 = 0;
///////////////////////    			



 



		

/**
 * [tLogRow_3 begin ] stop
 */




	
	/**
	 * [tMap_1 begin ] start
	 */

	

	
		
		sh("tMap_1");
		
	
	s(currentComponent="tMap_1");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"row3");
			
		int tos_count_tMap_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tMap_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tMap_1 = new StringBuilder();
                    log4jParamters_tMap_1.append("Parameters:");
                            log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
                        log4jParamters_tMap_1.append(" | ");
                            log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                        log4jParamters_tMap_1.append(" | ");
                            log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                        log4jParamters_tMap_1.append(" | ");
                            log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                        log4jParamters_tMap_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_1 - "  + (log4jParamters_tMap_1) );
                    } 
                } 
            new BytesLimit65535_tMap_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tMap_1", "tMap_1", "tMap");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			




// ###############################
// # Lookup's keys initialization
		int count_row3_tMap_1 = 0;
		
		int count_row4_tMap_1 = 0;
		
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
					globalMap.get( "tHash_Lookup_row4" ))
					;					
					
	

row4Struct row4HashKey = new row4Struct();
row4Struct row4Default = new row4Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_InsertRecords_tMap_1 = 0;
				
InsertRecordsStruct InsertRecords_tmp = new InsertRecordsStruct();
				int count_UpdateRecords_tMap_1 = 0;
				
UpdateRecordsStruct UpdateRecords_tmp = new UpdateRecordsStruct();
				int count_NoChangeRecords_tMap_1 = 0;
				
NoChangeRecordsStruct NoChangeRecords_tmp = new NoChangeRecordsStruct();
// ###############################

        
        



        









 



		

/**
 * [tMap_1 begin ] stop
 */




	
	/**
	 * [cdcHashJoblet_1_tMap_1 begin ] start
	 */

	

	
		
		sh("cdcHashJoblet_1_tMap_1");
		
	
	s(currentComponent="cdcHashJoblet_1_tMap_1");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"row1");
			
		int tos_count_cdcHashJoblet_1_tMap_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("cdcHashJoblet_1_tMap_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_cdcHashJoblet_1_tMap_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_cdcHashJoblet_1_tMap_1 = new StringBuilder();
                    log4jParamters_cdcHashJoblet_1_tMap_1.append("Parameters:");
                            log4jParamters_cdcHashJoblet_1_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
                        log4jParamters_cdcHashJoblet_1_tMap_1.append(" | ");
                            log4jParamters_cdcHashJoblet_1_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                        log4jParamters_cdcHashJoblet_1_tMap_1.append(" | ");
                            log4jParamters_cdcHashJoblet_1_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                        log4jParamters_cdcHashJoblet_1_tMap_1.append(" | ");
                            log4jParamters_cdcHashJoblet_1_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                        log4jParamters_cdcHashJoblet_1_tMap_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("cdcHashJoblet_1_tMap_1 - "  + (log4jParamters_cdcHashJoblet_1_tMap_1) );
                    } 
                } 
            new BytesLimit65535_cdcHashJoblet_1_tMap_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("cdcHashJoblet_1_tMap_1", "tMap_1", "tMap");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			




// ###############################
// # Lookup's keys initialization
		int count_row1_cdcHashJoblet_1_tMap_1 = 0;
		
// ###############################        

// ###############################
// # Vars initialization
// ###############################

// ###############################
// # Outputs initialization
				int count_row3_cdcHashJoblet_1_tMap_1 = 0;
				
row3Struct row3_tmp = new row3Struct();
// ###############################

        
        



        









 



		

/**
 * [cdcHashJoblet_1_tMap_1 begin ] stop
 */




	
	/**
	 * [tFileInputDelimited_1 begin ] start
	 */

	

	
		
		sh("tFileInputDelimited_1");
		
	
	s(currentComponent="tFileInputDelimited_1");
	
			
			
	
		int tos_count_tFileInputDelimited_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tFileInputDelimited_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tFileInputDelimited_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tFileInputDelimited_1 = new StringBuilder();
                    log4jParamters_tFileInputDelimited_1.append("Parameters:");
                            log4jParamters_tFileInputDelimited_1.append("USE_EXISTING_DYNAMIC" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("FILENAME" + " = " + "\"C:/Users/harsh/OneDrive/Desktop/customers_source.csv\"");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("CSV_OPTION" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("FIELDSEPARATOR" + " = " + "\",\"");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("HEADER" + " = " + "1");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("FOOTER" + " = " + "0");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("LIMIT" + " = " + "");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("REMOVE_EMPTY_ROW" + " = " + "true");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("UNCOMPRESS" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("DIE_ON_ERROR" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("RANDOM" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("TRIMALL" + " = " + "true");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("CHECK_FIELDS_NUM" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("CHECK_DATE" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("ENCODING" + " = " + "\"UTF-8\"");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("SPLITRECORD" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("ENABLE_DECODE" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                            log4jParamters_tFileInputDelimited_1.append("USE_HEADER_AS_IS" + " = " + "false");
                        log4jParamters_tFileInputDelimited_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tFileInputDelimited_1 - "  + (log4jParamters_tFileInputDelimited_1) );
                    } 
                } 
            new BytesLimit65535_tFileInputDelimited_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tFileInputDelimited_1", "tFileInputDelimited_1", "tFileInputDelimited");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			
	
	
	
 
	
	
	final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();
	
	
				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try{
					
						Object filename_tFileInputDelimited_1 = "C:/Users/harsh/OneDrive/Desktop/customers_source.csv";
						if(filename_tFileInputDelimited_1 instanceof java.io.InputStream){
							
			int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
			if(footer_value_tFileInputDelimited_1 >0 || random_value_tFileInputDelimited_1 > 0){
				throw new java.lang.Exception("When the input source is a stream,footer and random shouldn't be bigger than 0.");				
			}
		
						}
						try {
							fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited("C:/Users/harsh/OneDrive/Desktop/customers_source.csv", "UTF-8",",","\n",true,1,0,
									limit_tFileInputDelimited_1
								,-1, false);
						} catch(java.lang.Exception e) {
globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",e.getMessage());
							
								
									log.error("tFileInputDelimited_1 - " +e.getMessage());
								
								System.err.println(e.getMessage());
							
						}
					
				    
				    	log.info("tFileInputDelimited_1 - Retrieving records from the datasource.");
				    
					while (fid_tFileInputDelimited_1!=null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();
						
			    						row1 = null;			
												
									boolean whetherReject_tFileInputDelimited_1 = false;
									row1 = new row1Struct();
									try {
										
				int columnIndexWithD_tFileInputDelimited_1 = 0;
				
					String temp = ""; 
				
					columnIndexWithD_tFileInputDelimited_1 = 0;
					
						temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1).trim();
						if(temp.length() > 0) {
							
								try {
								
    								row1.cust_id = ParserUtils.parseTo_Integer(temp);
    							
    							} catch(java.lang.Exception ex_tFileInputDelimited_1) {
globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
										"cust_id", "row1", temp, ex_tFileInputDelimited_1), ex_tFileInputDelimited_1));
								}
    							
						} else {						
							
								
									row1.cust_id = null;
								
							
						}
					
				
					columnIndexWithD_tFileInputDelimited_1 = 1;
					
							row1.first_name = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1).trim();
						
				
					columnIndexWithD_tFileInputDelimited_1 = 2;
					
							row1.last_name = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1).trim();
						
				
					columnIndexWithD_tFileInputDelimited_1 = 3;
					
							row1.email = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1).trim();
						
				
				
										
										if(rowstate_tFileInputDelimited_1.getException()!=null) {
											throw rowstate_tFileInputDelimited_1.getException();
										}
										
										
							
			    					} catch (java.lang.Exception e) {
globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",e.getMessage());
			        					whetherReject_tFileInputDelimited_1 = true;
			        					
												log.error("tFileInputDelimited_1 - " +e.getMessage());
											
			                					System.err.println(e.getMessage());
			                					row1 = null;
			                				
										
			    					}
								
			log.debug("tFileInputDelimited_1 - Retrieving the record " + fid_tFileInputDelimited_1.getRowNumber() + ".");
		

 



		

/**
 * [tFileInputDelimited_1 begin ] stop
 */

	
	/**
	 * [tFileInputDelimited_1 main ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_1");
	
			
			
	

 


	tos_count_tFileInputDelimited_1++;

		

/**
 * [tFileInputDelimited_1 main ] stop
 */

	
	/**
	 * [tFileInputDelimited_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_1");
	
			
			
	

 



		

/**
 * [tFileInputDelimited_1 process_data_begin ] stop
 */

// Start of branch "row1"
if(row1 != null) { 



	
	/**
	 * [cdcHashJoblet_1_tMap_1 main ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_1_tMap_1");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"row1","tFileInputDelimited_1","tFileInputDelimited_1","tFileInputDelimited","cdcHashJoblet_1_tMap_1","tMap_1","tMap"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("row1 - " + (row1==null? "": row1.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_cdcHashJoblet_1_tMap_1 = false;
		
		// ###############################
		// # Input tables (lookups)
		
		boolean rejectedInnerJoin_cdcHashJoblet_1_tMap_1 = false;
		boolean mainRowRejected_cdcHashJoblet_1_tMap_1 = false;
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        // ###############################
        // ###############################
        // # Output tables

row3 = null;


// # Output table : 'row3'
count_row3_cdcHashJoblet_1_tMap_1++;

row3_tmp.cust_id = row1.cust_id ;
row3_tmp.first_name = row1.first_name ;
row3_tmp.last_name = row1.last_name ;
row3_tmp.email = row1.email ;
row3_tmp.hash_code = HashUtil.generateHash(row1.first_name + row1.last_name + row1.email);
row3 = row3_tmp;
log.debug("cdcHashJoblet_1_tMap_1 - Outputting the record " + count_row3_cdcHashJoblet_1_tMap_1 + " of the output table 'row3'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_cdcHashJoblet_1_tMap_1 = false;










 


	tos_count_cdcHashJoblet_1_tMap_1++;

		

/**
 * [cdcHashJoblet_1_tMap_1 main ] stop
 */

	
	/**
	 * [cdcHashJoblet_1_tMap_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_1_tMap_1");
	
			
			
	

 



		

/**
 * [cdcHashJoblet_1_tMap_1 process_data_begin ] stop
 */

// Start of branch "row3"
if(row3 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	s(currentComponent="tMap_1");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"row3","cdcHashJoblet_1_tMap_1","tMap_1","tMap","tMap_1","tMap_1","tMap"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("row3 - " + (row3==null? "": row3.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;
		
						row4Struct row4 = null;
					
		// ###############################
		// # Input tables (lookups)
		
		boolean rejectedInnerJoin_tMap_1 = false;
		boolean mainRowRejected_tMap_1 = false;
		

				///////////////////////////////////////////////
				// Starting Lookup Table "row4" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow4 = false;
       		  	    	
       		  	    	
 							row4Struct row4ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row4HashKey.cust_id = row3.cust_id ;
                        		    		

								
		                        	row4HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row4.lookup( row4HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4' and it contains more one result from keys :  row4.cust_id = '" + row4HashKey.cust_id + "'");
								} // G 071
							

							
                    		  	 
							   
                    		  	 
	       		  	    	row4Struct fromLookup_row4 = null;
							row4 = row4Default;
										 
							
								 
							
							
								if (tHash_Lookup_row4 !=null && tHash_Lookup_row4.hasNext()) { // G 099
								
							
								
								fromLookup_row4 = tHash_Lookup_row4.next();

							
							
								} // G 099
							
							

							if(fromLookup_row4 != null) {
								row4 = fromLookup_row4;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

InsertRecords = null;
UpdateRecords = null;
NoChangeRecords = null;


// # Output table : 'InsertRecords'
// # Filter conditions 
if( 

row4.cust_id == null

 ) {
count_InsertRecords_tMap_1++;

InsertRecords_tmp.cust_id = row3.cust_id ;
InsertRecords_tmp.first_name = row3.first_name ;
InsertRecords_tmp.last_name = row3.last_name ;
InsertRecords_tmp.email = row3.email ;
InsertRecords_tmp.hash_code = row3.hash_code ;
InsertRecords = InsertRecords_tmp;
log.debug("tMap_1 - Outputting the record " + count_InsertRecords_tMap_1 + " of the output table 'InsertRecords'.");

} // closing filter/reject

// # Output table : 'UpdateRecords'
// # Filter conditions 
if( 

row4.cust_id != null && !row3.hash_code.equals(row4.hash_code)

 ) {
count_UpdateRecords_tMap_1++;

UpdateRecords_tmp.cust_id = row3.cust_id ;
UpdateRecords_tmp.first_name = row3.first_name ;
UpdateRecords_tmp.last_name = row3.last_name ;
UpdateRecords_tmp.email = row3.email ;
UpdateRecords_tmp.hash_code = row3.hash_code ;
UpdateRecords = UpdateRecords_tmp;
log.debug("tMap_1 - Outputting the record " + count_UpdateRecords_tMap_1 + " of the output table 'UpdateRecords'.");

} // closing filter/reject

// # Output table : 'NoChangeRecords'
// # Filter conditions 
if( 

row4.cust_id != null && row3.hash_code.equals(row4.hash_code)

 ) {
count_NoChangeRecords_tMap_1++;

NoChangeRecords_tmp.cust_id = row3.cust_id ;
NoChangeRecords_tmp.first_name = row3.first_name ;
NoChangeRecords_tmp.last_name = row3.last_name ;
NoChangeRecords_tmp.email = row3.email ;
NoChangeRecords_tmp.hash_code = row3.hash_code ;
NoChangeRecords = NoChangeRecords_tmp;
log.debug("tMap_1 - Outputting the record " + count_NoChangeRecords_tMap_1 + " of the output table 'NoChangeRecords'.");

} // closing filter/reject
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_1 = false;










 


	tos_count_tMap_1++;

		

/**
 * [tMap_1 main ] stop
 */

	
	/**
	 * [tMap_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tMap_1");
	
			
			
	

 



		

/**
 * [tMap_1 process_data_begin ] stop
 */

// Start of branch "InsertRecords"
if(InsertRecords != null) { 



	
	/**
	 * [tLogRow_1 main ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"InsertRecords","tMap_1","tMap_1","tMap","tLogRow_1","tLogRow_1","tLogRow"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("InsertRecords - " + (InsertRecords==null? "": InsertRecords.toLogString()));
    			}
    		
///////////////////////		
						

				
				String[] row_tLogRow_1 = new String[5];
   				
	    		if(InsertRecords.cust_id != null) { //              
                 row_tLogRow_1[0]=    						    
				                String.valueOf(InsertRecords.cust_id)			
					          ;	
							
	    		} //			
    			   				
	    		if(InsertRecords.first_name != null) { //              
                 row_tLogRow_1[1]=    						    
				                String.valueOf(InsertRecords.first_name)			
					          ;	
							
	    		} //			
    			   				
	    		if(InsertRecords.last_name != null) { //              
                 row_tLogRow_1[2]=    						    
				                String.valueOf(InsertRecords.last_name)			
					          ;	
							
	    		} //			
    			   				
	    		if(InsertRecords.email != null) { //              
                 row_tLogRow_1[3]=    						    
				                String.valueOf(InsertRecords.email)			
					          ;	
							
	    		} //			
    			   				
	    		if(InsertRecords.hash_code != null) { //              
                 row_tLogRow_1[4]=    						    
				                String.valueOf(InsertRecords.hash_code)			
					          ;	
							
	    		} //			
    			 

				util_tLogRow_1.addRow(row_tLogRow_1);	
				nb_line_tLogRow_1++;
                	log.info("tLogRow_1 - Content of row "+nb_line_tLogRow_1+": " + TalendString.unionString("|",row_tLogRow_1));
//////

//////                    
                    
///////////////////////    			

 


	tos_count_tLogRow_1++;

		

/**
 * [tLogRow_1 main ] stop
 */

	
	/**
	 * [tLogRow_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	

 



		

/**
 * [tLogRow_1 process_data_begin ] stop
 */

	
	/**
	 * [tLogRow_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	

 



		

/**
 * [tLogRow_1 process_data_end ] stop
 */


} // End of branch "InsertRecords"




// Start of branch "UpdateRecords"
if(UpdateRecords != null) { 



	
	/**
	 * [tLogRow_2 main ] start
	 */

	

	
	
	s(currentComponent="tLogRow_2");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"UpdateRecords","tMap_1","tMap_1","tMap","tLogRow_2","tLogRow_2","tLogRow"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("UpdateRecords - " + (UpdateRecords==null? "": UpdateRecords.toLogString()));
    			}
    		
///////////////////////		
						

				
				String[] row_tLogRow_2 = new String[5];
   				
	    		if(UpdateRecords.cust_id != null) { //              
                 row_tLogRow_2[0]=    						    
				                String.valueOf(UpdateRecords.cust_id)			
					          ;	
							
	    		} //			
    			   				
	    		if(UpdateRecords.first_name != null) { //              
                 row_tLogRow_2[1]=    						    
				                String.valueOf(UpdateRecords.first_name)			
					          ;	
							
	    		} //			
    			   				
	    		if(UpdateRecords.last_name != null) { //              
                 row_tLogRow_2[2]=    						    
				                String.valueOf(UpdateRecords.last_name)			
					          ;	
							
	    		} //			
    			   				
	    		if(UpdateRecords.email != null) { //              
                 row_tLogRow_2[3]=    						    
				                String.valueOf(UpdateRecords.email)			
					          ;	
							
	    		} //			
    			   				
	    		if(UpdateRecords.hash_code != null) { //              
                 row_tLogRow_2[4]=    						    
				                String.valueOf(UpdateRecords.hash_code)			
					          ;	
							
	    		} //			
    			 

				util_tLogRow_2.addRow(row_tLogRow_2);	
				nb_line_tLogRow_2++;
                	log.info("tLogRow_2 - Content of row "+nb_line_tLogRow_2+": " + TalendString.unionString("|",row_tLogRow_2));
//////

//////                    
                    
///////////////////////    			

 


	tos_count_tLogRow_2++;

		

/**
 * [tLogRow_2 main ] stop
 */

	
	/**
	 * [tLogRow_2 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tLogRow_2");
	
			
			
	

 



		

/**
 * [tLogRow_2 process_data_begin ] stop
 */

	
	/**
	 * [tLogRow_2 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_2");
	
			
			
	

 



		

/**
 * [tLogRow_2 process_data_end ] stop
 */


} // End of branch "UpdateRecords"




// Start of branch "NoChangeRecords"
if(NoChangeRecords != null) { 



	
	/**
	 * [tLogRow_3 main ] start
	 */

	

	
	
	s(currentComponent="tLogRow_3");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"NoChangeRecords","tMap_1","tMap_1","tMap","tLogRow_3","tLogRow_3","tLogRow"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("NoChangeRecords - " + (NoChangeRecords==null? "": NoChangeRecords.toLogString()));
    			}
    		
///////////////////////		
						

				
				String[] row_tLogRow_3 = new String[5];
   				
	    		if(NoChangeRecords.cust_id != null) { //              
                 row_tLogRow_3[0]=    						    
				                String.valueOf(NoChangeRecords.cust_id)			
					          ;	
							
	    		} //			
    			   				
	    		if(NoChangeRecords.first_name != null) { //              
                 row_tLogRow_3[1]=    						    
				                String.valueOf(NoChangeRecords.first_name)			
					          ;	
							
	    		} //			
    			   				
	    		if(NoChangeRecords.last_name != null) { //              
                 row_tLogRow_3[2]=    						    
				                String.valueOf(NoChangeRecords.last_name)			
					          ;	
							
	    		} //			
    			   				
	    		if(NoChangeRecords.email != null) { //              
                 row_tLogRow_3[3]=    						    
				                String.valueOf(NoChangeRecords.email)			
					          ;	
							
	    		} //			
    			   				
	    		if(NoChangeRecords.hash_code != null) { //              
                 row_tLogRow_3[4]=    						    
				                String.valueOf(NoChangeRecords.hash_code)			
					          ;	
							
	    		} //			
    			 

				util_tLogRow_3.addRow(row_tLogRow_3);	
				nb_line_tLogRow_3++;
                	log.info("tLogRow_3 - Content of row "+nb_line_tLogRow_3+": " + TalendString.unionString("|",row_tLogRow_3));
//////

//////                    
                    
///////////////////////    			

 


	tos_count_tLogRow_3++;

		

/**
 * [tLogRow_3 main ] stop
 */

	
	/**
	 * [tLogRow_3 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tLogRow_3");
	
			
			
	

 



		

/**
 * [tLogRow_3 process_data_begin ] stop
 */

	
	/**
	 * [tLogRow_3 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_3");
	
			
			
	

 



		

/**
 * [tLogRow_3 process_data_end ] stop
 */


} // End of branch "NoChangeRecords"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tMap_1");
	
			
			
	

 



		

/**
 * [tMap_1 process_data_end ] stop
 */


} // End of branch "row3"




	
	/**
	 * [cdcHashJoblet_1_tMap_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_1_tMap_1");
	
			
			
	

 



		

/**
 * [cdcHashJoblet_1_tMap_1 process_data_end ] stop
 */


} // End of branch "row1"




	
	/**
	 * [tFileInputDelimited_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_1");
	
			
			
	

 



		

/**
 * [tFileInputDelimited_1 process_data_end ] stop
 */

	
	/**
	 * [tFileInputDelimited_1 end ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_1");
	
			
			
	



            }
            }finally{
                if(!((Object)("C:/Users/harsh/OneDrive/Desktop/customers_source.csv") instanceof java.io.InputStream)){
                	if(fid_tFileInputDelimited_1!=null){
                		fid_tFileInputDelimited_1.close();
                	}
                }
                if(fid_tFileInputDelimited_1!=null){
                	globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());
					
						log.info("tFileInputDelimited_1 - Retrieved records count: "+ fid_tFileInputDelimited_1.getRowNumber() + ".");
					
                }
			}
			  

 
                if(log.isDebugEnabled())
            log.debug("tFileInputDelimited_1 - "  + ("Done.") );

ok_Hash.put("tFileInputDelimited_1", true);
end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());




		

/**
 * [tFileInputDelimited_1 end ] stop
 */


	
	/**
	 * [cdcHashJoblet_1_tMap_1 end ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_1_tMap_1");
	
			
			
	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("cdcHashJoblet_1_tMap_1 - Written records count in the table 'row3': " + count_row3_cdcHashJoblet_1_tMap_1 + ".");





			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"row1",2,0,
			 			"tFileInputDelimited_1","tFileInputDelimited_1","tFileInputDelimited","cdcHashJoblet_1_tMap_1","tMap_1","tMap","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("cdcHashJoblet_1_tMap_1 - "  + ("Done.") );

ok_Hash.put("cdcHashJoblet_1_tMap_1", true);
end_Hash.put("cdcHashJoblet_1_tMap_1", System.currentTimeMillis());




		

/**
 * [cdcHashJoblet_1_tMap_1 end ] stop
 */


	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	s(currentComponent="tMap_1");
	
			
			
	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row4 != null) {
						tHash_Lookup_row4.endGet();
					}
					globalMap.remove( "tHash_Lookup_row4" );

					
					
				
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'InsertRecords': " + count_InsertRecords_tMap_1 + ".");
				log.debug("tMap_1 - Written records count in the table 'UpdateRecords': " + count_UpdateRecords_tMap_1 + ".");
				log.debug("tMap_1 - Written records count in the table 'NoChangeRecords': " + count_NoChangeRecords_tMap_1 + ".");





			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"row3",2,0,
			 			"cdcHashJoblet_1_tMap_1","tMap_1","tMap","tMap_1","tMap_1","tMap","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("tMap_1 - "  + ("Done.") );

ok_Hash.put("tMap_1", true);
end_Hash.put("tMap_1", System.currentTimeMillis());




		

/**
 * [tMap_1 end ] stop
 */


	
	/**
	 * [tLogRow_1 end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	


//////

                    
                    java.io.PrintStream consoleOut_tLogRow_1 = null;
                    if (globalMap.get("tLogRow_CONSOLE")!=null)
                    {
                    	consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
                    }
                    else
                    {
                    	consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
                    	globalMap.put("tLogRow_CONSOLE",consoleOut_tLogRow_1);
                    }
                    
                    consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
                    consoleOut_tLogRow_1.flush();
//////
globalMap.put("tLogRow_1_NB_LINE",nb_line_tLogRow_1);
                if(log.isInfoEnabled())
            log.info("tLogRow_1 - "  + ("Printed row count: ")  + (nb_line_tLogRow_1)  + (".") );

///////////////////////    			

			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"InsertRecords",2,0,
			 			"tMap_1","tMap_1","tMap","tLogRow_1","tLogRow_1","tLogRow","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("tLogRow_1 - "  + ("Done.") );

ok_Hash.put("tLogRow_1", true);
end_Hash.put("tLogRow_1", System.currentTimeMillis());




		

/**
 * [tLogRow_1 end ] stop
 */





	
	/**
	 * [tLogRow_2 end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_2");
	
			
			
	


//////

                    
                    java.io.PrintStream consoleOut_tLogRow_2 = null;
                    if (globalMap.get("tLogRow_CONSOLE")!=null)
                    {
                    	consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
                    }
                    else
                    {
                    	consoleOut_tLogRow_2 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
                    	globalMap.put("tLogRow_CONSOLE",consoleOut_tLogRow_2);
                    }
                    
                    consoleOut_tLogRow_2.println(util_tLogRow_2.format().toString());
                    consoleOut_tLogRow_2.flush();
//////
globalMap.put("tLogRow_2_NB_LINE",nb_line_tLogRow_2);
                if(log.isInfoEnabled())
            log.info("tLogRow_2 - "  + ("Printed row count: ")  + (nb_line_tLogRow_2)  + (".") );

///////////////////////    			

			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"UpdateRecords",2,0,
			 			"tMap_1","tMap_1","tMap","tLogRow_2","tLogRow_2","tLogRow","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("tLogRow_2 - "  + ("Done.") );

ok_Hash.put("tLogRow_2", true);
end_Hash.put("tLogRow_2", System.currentTimeMillis());




		

/**
 * [tLogRow_2 end ] stop
 */





	
	/**
	 * [tLogRow_3 end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_3");
	
			
			
	


//////

                    
                    java.io.PrintStream consoleOut_tLogRow_3 = null;
                    if (globalMap.get("tLogRow_CONSOLE")!=null)
                    {
                    	consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
                    }
                    else
                    {
                    	consoleOut_tLogRow_3 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
                    	globalMap.put("tLogRow_CONSOLE",consoleOut_tLogRow_3);
                    }
                    
                    consoleOut_tLogRow_3.println(util_tLogRow_3.format().toString());
                    consoleOut_tLogRow_3.flush();
//////
globalMap.put("tLogRow_3_NB_LINE",nb_line_tLogRow_3);
                if(log.isInfoEnabled())
            log.info("tLogRow_3 - "  + ("Printed row count: ")  + (nb_line_tLogRow_3)  + (".") );

///////////////////////    			

			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"NoChangeRecords",2,0,
			 			"tMap_1","tMap_1","tMap","tLogRow_3","tLogRow_3","tLogRow","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("tLogRow_3 - "  + ("Done.") );

ok_Hash.put("tLogRow_3", true);
end_Hash.put("tLogRow_3", System.currentTimeMillis());




		

/**
 * [tLogRow_3 end ] stop
 */










				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row4"); 
				     			
				try{
					
	
	/**
	 * [tFileInputDelimited_1 finally ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_1");
	
			
			
	

 



		

/**
 * [tFileInputDelimited_1 finally ] stop
 */


	
	/**
	 * [cdcHashJoblet_1_tMap_1 finally ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_1_tMap_1");
	
			
			
	

 



		

/**
 * [cdcHashJoblet_1_tMap_1 finally ] stop
 */


	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	s(currentComponent="tMap_1");
	
			
			
	

 



		

/**
 * [tMap_1 finally ] stop
 */


	
	/**
	 * [tLogRow_1 finally ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	

 



		

/**
 * [tLogRow_1 finally ] stop
 */





	
	/**
	 * [tLogRow_2 finally ] start
	 */

	

	
	
	s(currentComponent="tLogRow_2");
	
			
			
	

 



		

/**
 * [tLogRow_2 finally ] stop
 */





	
	/**
	 * [tLogRow_3 finally ] start
	 */

	

	
	
	s(currentComponent="tLogRow_3");
	
			
			
	

 



		

/**
 * [tLogRow_3 finally ] stop
 */










				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}
	


public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				
			    public String hash_code;

				public String getHash_code () {
					return this.hash_code;
				}

				public Boolean hash_codeIsNullable(){
				    return true;
				}
				public Boolean hash_codeIsKey(){
				    return false;
				}
				public Integer hash_codeLength(){
				    return null;
				}
				public Integer hash_codePrecision(){
				    return null;
				}
				public String hash_codeDefault(){
				
					return null;
				
				}
				public String hash_codeComment(){
				
				    return "";
				
				}
				public String hash_codePattern(){
				
					return "";
				
				}
				public String hash_codeOriginalDbColumnName(){
				
					return "hash_code";
				
				}

				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.cust_id == null) ? 0 : this.cust_id.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row4Struct other = (row4Struct) obj;
		
						if (this.cust_id == null) {
							if (other.cust_id != null)
								return false;
						
						} else if (!this.cust_id.equals(other.cust_id))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row4Struct other) {

		other.cust_id = this.cust_id;
	            other.first_name = this.first_name;
	            other.last_name = this.last_name;
	            other.email = this.email;
	            other.hash_code = this.hash_code;
	            
	}

	public void copyKeysDataTo(row4Struct other) {

		other.cust_id = this.cust_id;
	            	
	}



	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
						this.first_name = readString(dis,ois);
					
						this.last_name = readString(dis,ois);
					
						this.email = readString(dis,ois);
					
						this.hash_code = readString(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
						this.first_name = readString(dis,objectIn);
					
						this.last_name = readString(dis,objectIn);
					
						this.email = readString(dis,objectIn);
					
						this.hash_code = readString(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
						writeString(this.first_name, dos, oos);
					
						writeString(this.last_name, dos, oos);
					
						writeString(this.email, dos, oos);
					
						writeString(this.hash_code, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
						writeString(this.first_name, dos, objectOut);
					
						writeString(this.last_name, dos, objectOut);
					
						writeString(this.email, dos, objectOut);
					
						writeString(this.hash_code, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
		sb.append(",hash_code="+hash_code);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        				if(hash_code == null){
        					sb.append("<null>");
        				}else{
            				sb.append(hash_code);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row4Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.cust_id, other.cust_id);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_HARSHITHABOWRESHETTY_job = new byte[0];
    static byte[] commonByteArray_HARSHITHABOWRESHETTY_job = new byte[0];

	
			    public Integer cust_id;

				public Integer getCust_id () {
					return this.cust_id;
				}

				public Boolean cust_idIsNullable(){
				    return true;
				}
				public Boolean cust_idIsKey(){
				    return true;
				}
				public Integer cust_idLength(){
				    return null;
				}
				public Integer cust_idPrecision(){
				    return null;
				}
				public String cust_idDefault(){
				
					return null;
				
				}
				public String cust_idComment(){
				
				    return "";
				
				}
				public String cust_idPattern(){
				
					return "";
				
				}
				public String cust_idOriginalDbColumnName(){
				
					return "cust_id";
				
				}

				
			    public String first_name;

				public String getFirst_name () {
					return this.first_name;
				}

				public Boolean first_nameIsNullable(){
				    return true;
				}
				public Boolean first_nameIsKey(){
				    return false;
				}
				public Integer first_nameLength(){
				    return null;
				}
				public Integer first_namePrecision(){
				    return null;
				}
				public String first_nameDefault(){
				
					return null;
				
				}
				public String first_nameComment(){
				
				    return "";
				
				}
				public String first_namePattern(){
				
					return "";
				
				}
				public String first_nameOriginalDbColumnName(){
				
					return "first_name";
				
				}

				
			    public String last_name;

				public String getLast_name () {
					return this.last_name;
				}

				public Boolean last_nameIsNullable(){
				    return true;
				}
				public Boolean last_nameIsKey(){
				    return false;
				}
				public Integer last_nameLength(){
				    return null;
				}
				public Integer last_namePrecision(){
				    return null;
				}
				public String last_nameDefault(){
				
					return null;
				
				}
				public String last_nameComment(){
				
				    return "";
				
				}
				public String last_namePattern(){
				
					return "";
				
				}
				public String last_nameOriginalDbColumnName(){
				
					return "last_name";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_HARSHITHABOWRESHETTY_job.length) {
				if(length < 1024 && commonByteArray_HARSHITHABOWRESHETTY_job.length == 0) {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[1024];
				} else {
   					commonByteArray_HARSHITHABOWRESHETTY_job = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length);
			strReturn = new String(commonByteArray_HARSHITHABOWRESHETTY_job, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_HARSHITHABOWRESHETTY_job) {

        	try {

        		int length = 0;
		
						this.cust_id = readInteger(dis);
					
					this.first_name = readString(dis);
					
					this.last_name = readString(dis);
					
					this.email = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.cust_id,dos);
					
					// String
				
						writeString(this.first_name,dos);
					
					// String
				
						writeString(this.last_name,dos);
					
					// String
				
						writeString(this.email,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("cust_id="+String.valueOf(cust_id));
		sb.append(",first_name="+first_name);
		sb.append(",last_name="+last_name);
		sb.append(",email="+email);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(cust_id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(cust_id);
            			}
            		
        			sb.append("|");
        		
        				if(first_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(first_name);
            			}
            		
        			sb.append("|");
        		
        				if(last_name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(last_name);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

	final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_2", "FfBVgS_");

	
		String iterateId = "";
	
	
	String currentComponent = "";
	s("none");
	String cLabel =  null;
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row2Struct row2 = new row2Struct();
row4Struct row4 = new row4Struct();





	
	/**
	 * [tAdvancedHash_row4 begin ] start
	 */

	

	
		
		sh("tAdvancedHash_row4");
		
	
	s(currentComponent="tAdvancedHash_row4");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"row4");
			
		int tos_count_tAdvancedHash_row4 = 0;
		
			if(enableLogStash) {
				talendJobLog.addCM("tAdvancedHash_row4", "tAdvancedHash_row4", "tAdvancedHash");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

			   		// connection name:row4
			   		// source node:cdcHashJoblet_2_tMap_1 - inputs:(row2) outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
			   		// linked node: tMap_1 - inputs:(row3,row4) outputs:(InsertRecords,UpdateRecords,NoChangeRecords)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row4Struct>getLookup(matchingModeEnum_row4);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);
		   	   	   
				
           

 



		

/**
 * [tAdvancedHash_row4 begin ] stop
 */




	
	/**
	 * [cdcHashJoblet_2_tMap_1 begin ] start
	 */

	

	
		
		sh("cdcHashJoblet_2_tMap_1");
		
	
	s(currentComponent="cdcHashJoblet_2_tMap_1");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"row2");
			
		int tos_count_cdcHashJoblet_2_tMap_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("cdcHashJoblet_2_tMap_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_cdcHashJoblet_2_tMap_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_cdcHashJoblet_2_tMap_1 = new StringBuilder();
                    log4jParamters_cdcHashJoblet_2_tMap_1.append("Parameters:");
                            log4jParamters_cdcHashJoblet_2_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
                        log4jParamters_cdcHashJoblet_2_tMap_1.append(" | ");
                            log4jParamters_cdcHashJoblet_2_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                        log4jParamters_cdcHashJoblet_2_tMap_1.append(" | ");
                            log4jParamters_cdcHashJoblet_2_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                        log4jParamters_cdcHashJoblet_2_tMap_1.append(" | ");
                            log4jParamters_cdcHashJoblet_2_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                        log4jParamters_cdcHashJoblet_2_tMap_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("cdcHashJoblet_2_tMap_1 - "  + (log4jParamters_cdcHashJoblet_2_tMap_1) );
                    } 
                } 
            new BytesLimit65535_cdcHashJoblet_2_tMap_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("cdcHashJoblet_2_tMap_1", "tMap_1", "tMap");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			




// ###############################
// # Lookup's keys initialization
		int count_row2_cdcHashJoblet_2_tMap_1 = 0;
		
// ###############################        

// ###############################
// # Vars initialization
// ###############################

// ###############################
// # Outputs initialization
				int count_row4_cdcHashJoblet_2_tMap_1 = 0;
				
row4Struct row4_tmp = new row4Struct();
// ###############################

        
        



        









 



		

/**
 * [cdcHashJoblet_2_tMap_1 begin ] stop
 */




	
	/**
	 * [tFileInputDelimited_2 begin ] start
	 */

	

	
		
		sh("tFileInputDelimited_2");
		
	
	s(currentComponent="tFileInputDelimited_2");
	
			
			
	
		int tos_count_tFileInputDelimited_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tFileInputDelimited_2 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tFileInputDelimited_2{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tFileInputDelimited_2 = new StringBuilder();
                    log4jParamters_tFileInputDelimited_2.append("Parameters:");
                            log4jParamters_tFileInputDelimited_2.append("USE_EXISTING_DYNAMIC" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("FILENAME" + " = " + "\"C:/Users/harsh/OneDrive/Desktop/customers_target.csv\"");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("CSV_OPTION" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("ROWSEPARATOR" + " = " + "\"\\n\"");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("FIELDSEPARATOR" + " = " + "\",\"");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("HEADER" + " = " + "1");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("FOOTER" + " = " + "0");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("LIMIT" + " = " + "");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("REMOVE_EMPTY_ROW" + " = " + "true");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("UNCOMPRESS" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("DIE_ON_ERROR" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("ADVANCED_SEPARATOR" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("RANDOM" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("TRIMALL" + " = " + "true");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("CHECK_FIELDS_NUM" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("CHECK_DATE" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("ENCODING" + " = " + "\"UTF-8\"");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("SPLITRECORD" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("ENABLE_DECODE" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                            log4jParamters_tFileInputDelimited_2.append("USE_HEADER_AS_IS" + " = " + "false");
                        log4jParamters_tFileInputDelimited_2.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tFileInputDelimited_2 - "  + (log4jParamters_tFileInputDelimited_2) );
                    } 
                } 
            new BytesLimit65535_tFileInputDelimited_2().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tFileInputDelimited_2", "tFileInputDelimited_2", "tFileInputDelimited");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			
	
	
	
 
	
	
	final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();
	
	
				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try{
					
						Object filename_tFileInputDelimited_2 = "C:/Users/harsh/OneDrive/Desktop/customers_target.csv";
						if(filename_tFileInputDelimited_2 instanceof java.io.InputStream){
							
			int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
			if(footer_value_tFileInputDelimited_2 >0 || random_value_tFileInputDelimited_2 > 0){
				throw new java.lang.Exception("When the input source is a stream,footer and random shouldn't be bigger than 0.");				
			}
		
						}
						try {
							fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited("C:/Users/harsh/OneDrive/Desktop/customers_target.csv", "UTF-8",",","\n",true,1,0,
									limit_tFileInputDelimited_2
								,-1, false);
						} catch(java.lang.Exception e) {
globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",e.getMessage());
							
								
									log.error("tFileInputDelimited_2 - " +e.getMessage());
								
								System.err.println(e.getMessage());
							
						}
					
				    
				    	log.info("tFileInputDelimited_2 - Retrieving records from the datasource.");
				    
					while (fid_tFileInputDelimited_2!=null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();
						
			    						row2 = null;			
												
									boolean whetherReject_tFileInputDelimited_2 = false;
									row2 = new row2Struct();
									try {
										
				int columnIndexWithD_tFileInputDelimited_2 = 0;
				
					String temp = ""; 
				
					columnIndexWithD_tFileInputDelimited_2 = 0;
					
						temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2).trim();
						if(temp.length() > 0) {
							
								try {
								
    								row2.cust_id = ParserUtils.parseTo_Integer(temp);
    							
    							} catch(java.lang.Exception ex_tFileInputDelimited_2) {
globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
										"cust_id", "row2", temp, ex_tFileInputDelimited_2), ex_tFileInputDelimited_2));
								}
    							
						} else {						
							
								
									row2.cust_id = null;
								
							
						}
					
				
					columnIndexWithD_tFileInputDelimited_2 = 1;
					
							row2.first_name = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2).trim();
						
				
					columnIndexWithD_tFileInputDelimited_2 = 2;
					
							row2.last_name = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2).trim();
						
				
					columnIndexWithD_tFileInputDelimited_2 = 3;
					
							row2.email = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2).trim();
						
				
				
										
										if(rowstate_tFileInputDelimited_2.getException()!=null) {
											throw rowstate_tFileInputDelimited_2.getException();
										}
										
										
							
			    					} catch (java.lang.Exception e) {
globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",e.getMessage());
			        					whetherReject_tFileInputDelimited_2 = true;
			        					
												log.error("tFileInputDelimited_2 - " +e.getMessage());
											
			                					System.err.println(e.getMessage());
			                					row2 = null;
			                				
										
			    					}
								
			log.debug("tFileInputDelimited_2 - Retrieving the record " + fid_tFileInputDelimited_2.getRowNumber() + ".");
		

 



		

/**
 * [tFileInputDelimited_2 begin ] stop
 */

	
	/**
	 * [tFileInputDelimited_2 main ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_2");
	
			
			
	

 


	tos_count_tFileInputDelimited_2++;

		

/**
 * [tFileInputDelimited_2 main ] stop
 */

	
	/**
	 * [tFileInputDelimited_2 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_2");
	
			
			
	

 



		

/**
 * [tFileInputDelimited_2 process_data_begin ] stop
 */

// Start of branch "row2"
if(row2 != null) { 



	
	/**
	 * [cdcHashJoblet_2_tMap_1 main ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_2_tMap_1");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"row2","tFileInputDelimited_2","tFileInputDelimited_2","tFileInputDelimited","cdcHashJoblet_2_tMap_1","tMap_1","tMap"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("row2 - " + (row2==null? "": row2.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_cdcHashJoblet_2_tMap_1 = false;
		
		// ###############################
		// # Input tables (lookups)
		
		boolean rejectedInnerJoin_cdcHashJoblet_2_tMap_1 = false;
		boolean mainRowRejected_cdcHashJoblet_2_tMap_1 = false;
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        // ###############################
        // ###############################
        // # Output tables

row4 = null;


// # Output table : 'row4'
count_row4_cdcHashJoblet_2_tMap_1++;

row4_tmp.cust_id = row2.cust_id ;
row4_tmp.first_name = row2.first_name ;
row4_tmp.last_name = row2.last_name ;
row4_tmp.email = row2.email ;
row4_tmp.hash_code = HashUtil.generateHash(row2.first_name + row2.last_name + row2.email);
row4 = row4_tmp;
log.debug("cdcHashJoblet_2_tMap_1 - Outputting the record " + count_row4_cdcHashJoblet_2_tMap_1 + " of the output table 'row4'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_cdcHashJoblet_2_tMap_1 = false;










 


	tos_count_cdcHashJoblet_2_tMap_1++;

		

/**
 * [cdcHashJoblet_2_tMap_1 main ] stop
 */

	
	/**
	 * [cdcHashJoblet_2_tMap_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_2_tMap_1");
	
			
			
	

 



		

/**
 * [cdcHashJoblet_2_tMap_1 process_data_begin ] stop
 */

// Start of branch "row4"
if(row4 != null) { 



	
	/**
	 * [tAdvancedHash_row4 main ] start
	 */

	

	
	
	s(currentComponent="tAdvancedHash_row4");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"row4","cdcHashJoblet_2_tMap_1","tMap_1","tMap","tAdvancedHash_row4","tAdvancedHash_row4","tAdvancedHash"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("row4 - " + (row4==null? "": row4.toLogString()));
    			}
    		


			   
			   

					row4Struct row4_HashRow = new row4Struct();
		   	   	   
				
				row4_HashRow.cust_id = row4.cust_id;
				
				row4_HashRow.first_name = row4.first_name;
				
				row4_HashRow.last_name = row4.last_name;
				
				row4_HashRow.email = row4.email;
				
				row4_HashRow.hash_code = row4.hash_code;
				
			tHash_Lookup_row4.put(row4_HashRow);
			
            




 


	tos_count_tAdvancedHash_row4++;

		

/**
 * [tAdvancedHash_row4 main ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tAdvancedHash_row4");
	
			
			
	

 



		

/**
 * [tAdvancedHash_row4 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tAdvancedHash_row4");
	
			
			
	

 



		

/**
 * [tAdvancedHash_row4 process_data_end ] stop
 */


} // End of branch "row4"




	
	/**
	 * [cdcHashJoblet_2_tMap_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_2_tMap_1");
	
			
			
	

 



		

/**
 * [cdcHashJoblet_2_tMap_1 process_data_end ] stop
 */


} // End of branch "row2"




	
	/**
	 * [tFileInputDelimited_2 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_2");
	
			
			
	

 



		

/**
 * [tFileInputDelimited_2 process_data_end ] stop
 */

	
	/**
	 * [tFileInputDelimited_2 end ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_2");
	
			
			
	



            }
            }finally{
                if(!((Object)("C:/Users/harsh/OneDrive/Desktop/customers_target.csv") instanceof java.io.InputStream)){
                	if(fid_tFileInputDelimited_2!=null){
                		fid_tFileInputDelimited_2.close();
                	}
                }
                if(fid_tFileInputDelimited_2!=null){
                	globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());
					
						log.info("tFileInputDelimited_2 - Retrieved records count: "+ fid_tFileInputDelimited_2.getRowNumber() + ".");
					
                }
			}
			  

 
                if(log.isDebugEnabled())
            log.debug("tFileInputDelimited_2 - "  + ("Done.") );

ok_Hash.put("tFileInputDelimited_2", true);
end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());




		

/**
 * [tFileInputDelimited_2 end ] stop
 */


	
	/**
	 * [cdcHashJoblet_2_tMap_1 end ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_2_tMap_1");
	
			
			
	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("cdcHashJoblet_2_tMap_1 - Written records count in the table 'row4': " + count_row4_cdcHashJoblet_2_tMap_1 + ".");





			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"row2",2,0,
			 			"tFileInputDelimited_2","tFileInputDelimited_2","tFileInputDelimited","cdcHashJoblet_2_tMap_1","tMap_1","tMap","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("cdcHashJoblet_2_tMap_1 - "  + ("Done.") );

ok_Hash.put("cdcHashJoblet_2_tMap_1", true);
end_Hash.put("cdcHashJoblet_2_tMap_1", System.currentTimeMillis());




		

/**
 * [cdcHashJoblet_2_tMap_1 end ] stop
 */


	
	/**
	 * [tAdvancedHash_row4 end ] start
	 */

	

	
	
	s(currentComponent="tAdvancedHash_row4");
	
			
			
	

tHash_Lookup_row4.endPut();

			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"row4",2,0,
			 			"cdcHashJoblet_2_tMap_1","tMap_1","tMap","tAdvancedHash_row4","tAdvancedHash_row4","tAdvancedHash","output")) {
						talendJobLogProcess(globalMap);
					}
				
 

ok_Hash.put("tAdvancedHash_row4", true);
end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());




		

/**
 * [tAdvancedHash_row4 end ] stop
 */







				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tFileInputDelimited_2 finally ] start
	 */

	

	
	
	s(currentComponent="tFileInputDelimited_2");
	
			
			
	

 



		

/**
 * [tFileInputDelimited_2 finally ] stop
 */


	
	/**
	 * [cdcHashJoblet_2_tMap_1 finally ] start
	 */

	

	
	
	s(currentComponent="cdcHashJoblet_2_tMap_1");
	
			
			
	

 



		

/**
 * [cdcHashJoblet_2_tMap_1 finally ] stop
 */


	
	/**
	 * [tAdvancedHash_row4 finally ] start
	 */

	

	
	
	s(currentComponent="tAdvancedHash_row4");
	
			
			
	

 



		

/**
 * [tAdvancedHash_row4 finally ] stop
 */







				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}
	


public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

	final boolean execStat = this.execStat;


	
		String iterateId = "";
	
	
	String currentComponent = "";
	s("none");
	String cLabel =  null;
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;





	
	/**
	 * [talendJobLog begin ] start
	 */

	

	
		
		sh("talendJobLog");
		
	
	s(currentComponent="talendJobLog");
	
			
			
	
		int tos_count_talendJobLog = 0;
		

	for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
		org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
			.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid).custom("father_pid", fatherPid).custom("root_pid", rootPid);
		org.talend.logging.audit.Context log_context_talendJobLog = null;
		
		
		if(jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE){
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
			
			log_context_talendJobLog = builder_talendJobLog
				.sourceId(jcm.sourceId).sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
				.targetId(jcm.targetId).targetLabel(jcm.targetLabel).targetConnectorType(jcm.targetComponentName)
				.connectionName(jcm.current_connector).rows(jcm.row_count).duration(duration).build();
			auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
			log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
			auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
		
			log_context_talendJobLog = builder_talendJobLog
				.timestamp(jcm.moment).duration(duration).status(jcm.status).build();
			auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
			log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
				.connectorType(jcm.component_name).connectorId(jcm.component_id).connectorLabel(jcm.component_label).build();
			auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {//log current component input line
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
			
			log_context_talendJobLog = builder_talendJobLog
				.connectorType(jcm.component_name).connectorId(jcm.component_id).connectorLabel(jcm.component_label)
				.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
				.rows(jcm.total_row_number).duration(duration).build();
			auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {//log current component output/reject line
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
			
			log_context_talendJobLog = builder_talendJobLog
				.connectorType(jcm.component_name).connectorId(jcm.component_id).connectorLabel(jcm.component_label)
				.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
				.rows(jcm.total_row_number).duration(duration).build();
			auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
			java.lang.Exception e_talendJobLog = jcm.exception;
			if(e_talendJobLog!=null) {
				try(java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
					e_talendJobLog.printStackTrace(pw_talendJobLog);
					builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
				}
			}

			if(jcm.extra_info!=null) {
				builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
			}
				
			log_context_talendJobLog = builder_talendJobLog
				.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
				.connectorId(jcm.component_id)
				.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label).build();

			auditLogger_talendJobLog.exception(log_context_talendJobLog);
		}
		
		
		
	}

 



		

/**
 * [talendJobLog begin ] stop
 */

	
	/**
	 * [talendJobLog main ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 


	tos_count_talendJobLog++;

		

/**
 * [talendJobLog main ] stop
 */

	
	/**
	 * [talendJobLog process_data_begin ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 



		

/**
 * [talendJobLog process_data_begin ] stop
 */

	
	/**
	 * [talendJobLog process_data_end ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 



		

/**
 * [talendJobLog process_data_end ] stop
 */

	
	/**
	 * [talendJobLog end ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 

ok_Hash.put("talendJobLog", true);
end_Hash.put("talendJobLog", System.currentTimeMillis());




		

/**
 * [talendJobLog end ] stop
 */

				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [talendJobLog finally ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 



		

/**
 * [talendJobLog finally ] stop
 */

				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "Default";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";
    
    private boolean enableLogStash;
    private boolean enableLineage;

    private boolean execStat = true;
    
    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };


    protected PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";
    
    
    private final static java.util.Properties jobInfo = new java.util.Properties();
    private final static java.util.Map<String,String> mdcInfo = new java.util.HashMap<>();
    private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();


    public static void main(String[] args){
        final job jobClass = new job();

        int exitCode = jobClass.runJobInTOS(args);
	        if(exitCode==0){
		        log.info("TalendJob: 'job' - Done.");
	        }

        System.exit(exitCode);
    }
	

	
	
	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if(path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
			readJobInfo(new java.io.File(BUILD_PATH));
	}

    private void readJobInfo(java.io.File jobInfoFile){
	
        if(jobInfoFile.exists()) {
            try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
            	jobInfo.load(is);
            } catch (IOException e) {
            	 
                log.debug("Read jobInfo.properties file fail: " + e.getMessage());
                
            }
        }
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s",
				projectName,jobName,jobInfo.getProperty("gitCommitId"), "8.0.1.20250625_0954-patch"));
		
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";
	   	
        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }

        final boolean enableCBP = false;
        boolean inOSGi = routines.system.BundleUtils.inOSGi();

        if (!inOSGi && isCBPClientPresent) {
        if(org.talend.metrics.CBPClient.getInstanceForCurrentVM() == null) {
            try {
                org.talend.metrics.CBPClient.startListenIfNotStarted(enableCBP, true);
            } catch (java.lang.Exception e) {
                errorCode = 1;
                status = "failure";
                e.printStackTrace();
                return 1;
            }
        }
        }
        
        enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

	        if(!"".equals(log4jLevel)){
	        	
				
				
				if("trace".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.TRACE);
				}else if("debug".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.DEBUG);
				}else if("info".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.INFO);
				}else if("warn".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.WARN);
				}else if("error".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.ERROR);
				}else if("fatal".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.FATAL);
				}else if ("off".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.OFF);
				}
				org.apache.logging.log4j.core.config.Configurator.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());
				
			}

	        getjobInfo();
			log.info("TalendJob: 'job' - Start.");
		

                java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
                for(Object jobInfoKey: jobInfoKeys) {
                    org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
                }
                org.slf4j.MDC.put("_pid", pid);
                org.slf4j.MDC.put("_rootPid", rootPid);
                org.slf4j.MDC.put("_fatherPid", fatherPid);
                org.slf4j.MDC.put("_projectName", projectName);
                org.slf4j.MDC.put("_startTimestamp",java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC ).format( java.time.format.DateTimeFormatter.ISO_INSTANT ));
                org.slf4j.MDC.put("_jobRepositoryId","_1_4oUGhHEfCbwrwNwoBUXQ");
                org.slf4j.MDC.put("_compiledAtTimestamp","2025-07-24T06:23:56.212872Z");

                java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
                String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
                if (mxNameTable.length == 2) {
                    org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
                } else {
                    org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
                }

		
		
			if(enableLogStash) {
				java.util.Properties properties_talendJobLog = new java.util.Properties();
				properties_talendJobLog.setProperty("root.logger", "audit");
				properties_talendJobLog.setProperty("encoding", "UTF-8");
				properties_talendJobLog.setProperty("application.name", "Talend Studio");
				properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
				properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
				properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
				properties_talendJobLog.setProperty("log.appender", "file");
				properties_talendJobLog.setProperty("appender.file.path", "audit.json");
				properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
				properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
				properties_talendJobLog.setProperty("host", "false");

				System.getProperties().stringPropertyNames().stream()
					.filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()), System.getProperty(key)));

				
				
				
				org.apache.logging.log4j.core.config.Configurator.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);
				
				auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory.createJobAuditLogger(properties_talendJobLog);
			}
		

        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

            org.slf4j.MDC.put("_pid", pid);

        if (rootPid==null) {
            rootPid = pid;
        }

            org.slf4j.MDC.put("_rootPid", rootPid);

        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }
            org.slf4j.MDC.put("_fatherPid", fatherPid);

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }

        try {
            java.util.Dictionary<String, Object> jobProperties = null;
            if (inOSGi) {
                jobProperties = routines.system.BundleUtils.getJobProperties(jobName);
    
                if (jobProperties != null && jobProperties.get("context") != null) {
                    contextStr = (String)jobProperties.get("context");
                }

                if (jobProperties != null && jobProperties.get("taskExecutionId") != null) {
                    taskExecutionId = (String)jobProperties.get("taskExecutionId");
                }

                // extract ids from parent route
                if(null == taskExecutionId || taskExecutionId.isEmpty()){
                    for(String arg : args) {
                        if(arg.startsWith("--context_param")
                                && (arg.contains("taskExecutionId") || arg.contains("jobExecutionId"))){

                            String keyValue = arg.replace("--context_param", "");
                            String[] parts = keyValue.split("=");
                            String[] cleanParts = java.util.Arrays.stream(parts)
                                    .filter(s -> !s.isEmpty())
                                    .toArray(String[]::new);
                            if (cleanParts.length == 2) {
                                String key = cleanParts[0];
                                String value = cleanParts[1];
                                if ("taskExecutionId".equals(key.trim()) && null != value) {
                                    taskExecutionId = value.trim();
                                }else if ("jobExecutionId".equals(key.trim()) && null != value) {
                                    jobExecutionId = value.trim();
                                }
                            }
                        }
                    }
                }
            }

            // first load default key-value pairs from application.properties
            if(isStandaloneMS) {
                context.putAll(this.getDefaultProperties());
            }
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = job.class.getClassLoader().getResourceAsStream("harshithabowreshetty/job_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = job.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
            }
            if (inContext != null) {
                try {
                    //defaultProps is in order to keep the original context value
                    if(context != null && context.isEmpty()) {
    	                defaultProps.load(inContext);
    	                if (inOSGi && jobProperties != null) {
                             java.util.Enumeration<String> keys = jobProperties.keys();
                             while (keys.hasMoreElements()) {
                                 String propKey = keys.nextElement();
                                 if (defaultProps.containsKey(propKey)) {
                                     defaultProps.put(propKey, (String) jobProperties.get(propKey));
                                 }
                             }
    	                }
    	                context = new ContextProperties(defaultProps);
                    }
                    if(isStandaloneMS) {
                        // override context key-value pairs if provided using --context=contextName
                        defaultProps.load(inContext);
                        context.putAll(defaultProps);
                    }
                } finally {
                    inContext.close();
                }
            } else if (!isDefaultContext) {
                //print info and job continue to run, for case: context_param is not empty.
                System.err.println("Could not find the context " + contextStr);
            }
            // override key-value pairs if provided via --config.location=file1.file2 OR --config.additional-location=file1,file2
            if(isStandaloneMS) {
                context.putAll(this.getAdditionalProperties());
            }
            
            // override key-value pairs if provide via command line like --key1=value1,--key2=value2
            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
            class ContextProcessing {
                private void processContext_0() {
                } 
                public void processAllContext() {
                        processContext_0();
                }
            }

            new ContextProcessing().processAllContext();
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }

        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,ContextProperties.class,parametersToEncrypt));

            org.slf4j.MDC.put("_context", contextStr);
            log.info("TalendJob: 'job' - Started.");
            java.util.Optional.ofNullable(org.slf4j.MDC.getCopyOfContextMap()).ifPresent(mdcInfo::putAll);

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();


this.globalResumeTicket = true;//to run tPreJob




		if(enableLogStash) {
	        talendJobLog.addJobStartMessage();
	        try {
	            talendJobLogProcess(globalMap);
	        } catch (java.lang.Exception e) {
	            e.printStackTrace();
	        }
        }

this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tFileInputDelimited_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tFileInputDelimited_1) {
globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

e_tFileInputDelimited_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : job");
        }
		if(enableLogStash) {
	        talendJobLog.addJobEndMessage(startTime, end, status);
	        try {
	            talendJobLogProcess(globalMap);
	        } catch (java.lang.Exception e) {
	            e.printStackTrace();
	        }
        }



if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    if (!inOSGi && isCBPClientPresent) {
    if(org.talend.metrics.CBPClient.getInstanceForCurrentVM() != null) {
        s("none");
        org.talend.metrics.CBPClient.getInstanceForCurrentVM().sendData();
    }
    }
    

    int returnCode = 0;


    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");
    resumeUtil.flush();


        org.slf4j.MDC.remove("_subJobName");
        org.slf4j.MDC.remove("_subJobPid");
        org.slf4j.MDC.remove("_systemPid");
        log.info("TalendJob: 'job' - Finished - status: " + status + " returnCode: " + returnCode );

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {
  // add CBP code for OSGI Executions
  if (null != taskExecutionId && !taskExecutionId.isEmpty()) {
     try {
	   org.talend.metrics.DataReadTracker.setExecutionId(taskExecutionId, jobExecutionId, false);
	   org.talend.metrics.DataReadTracker.sealCounter();
	   org.talend.metrics.DataReadTracker.reset();
	} catch (Exception | NoClassDefFoundError e) {
	   // ignore
	}
  }



    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();






        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        } else if (arg.startsWith("--context_file")) {
        	String keyValue = arg.substring(15);
        	String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
        	java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
            try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int index = -1;
                    if ( (index = line.indexOf('=')) > -1) {
							if (line.startsWith("--context_param")) {
								if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
									context_param.put(line.substring(16, index), routines.system.PasswordEncryptUtil.decryptPassword(
											line.substring(index + 1)));
								} else {
									context_param.put(line.substring(16, index), line.substring(index + 1));
								}
							}else {//--context_type
								context_param.setContextType(line.substring(15, index), line.substring(index + 1));
							}
                    }
                }
            } catch (java.io.IOException e) {
            	System.err.println("Could not load the context file: " + filePath);
                e.printStackTrace();
            }
        } else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {//for trunjob call
		    final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     234719 characters generated by Talend Cloud Data Management Platform 
 *     on the 24 July 2025 at 11:53:56 am IST
 ************************************************************************************************/