package com.catic.mobilehos.webservice.timing;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.12
 * 2014-08-14T17:36:28.060+08:00
 * Generated source version: 2.7.12
 * 
 */
@WebService(targetNamespace = "http://timing.webService.mobilehos.catic.com/", name = "TimingService")
@XmlSeeAlso({ObjectFactory.class})
public interface TimingService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAppRegPeriod", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetAppRegPeriod")
    @WebMethod
    @ResponseWrapper(localName = "getAppRegPeriodResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetAppRegPeriodResponse")
    public java.lang.String getAppRegPeriod();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getDoctors", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetDoctors")
    @WebMethod
    @ResponseWrapper(localName = "getDoctorsResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetDoctorsResponse")
    public java.lang.String getDoctors();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getHisDepartmentDoctor", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetHisDepartmentDoctor")
    @WebMethod
    @ResponseWrapper(localName = "getHisDepartmentDoctorResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetHisDepartmentDoctorResponse")
    public java.lang.String getHisDepartmentDoctor();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getHisRoomQueueSn", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetHisRoomQueueSn")
    @WebMethod
    @ResponseWrapper(localName = "getHisRoomQueueSnResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetHisRoomQueueSnResponse")
    public java.lang.String getHisRoomQueueSn();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAppRegOrderType", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetAppRegOrderType")
    @WebMethod
    @ResponseWrapper(localName = "getAppRegOrderTypeResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetAppRegOrderTypeResponse")
    public java.lang.String getAppRegOrderType();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getTimetable", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetTimetable")
    @WebMethod
    @ResponseWrapper(localName = "getTimetableResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetTimetableResponse")
    public java.lang.String getTimetable(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getHisAppRegException", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetHisAppRegException")
    @WebMethod
    @ResponseWrapper(localName = "getHisAppRegExceptionResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetHisAppRegExceptionResponse")
    public java.lang.String getHisAppRegException(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getDepartments", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetDepartments")
    @WebMethod
    @ResponseWrapper(localName = "getDepartmentsResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetDepartmentsResponse")
    public java.lang.String getDepartments();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getRooms", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetRooms")
    @WebMethod
    @ResponseWrapper(localName = "getRoomsResponse", targetNamespace = "http://timing.webService.mobilehos.catic.com/", className = "com.catic.mobilehos.webservice.timing.GetRoomsResponse")
    public java.lang.String getRooms();
}
