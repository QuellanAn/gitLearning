import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.catic.mobilehos.po.EmployeePO;
import com.catic.mobilehos.service.EmployeeService;
import com.catic.mobilehos.service.EmployeeServiceImpl;
import com.catic.mobilehos.service.vo.Page;


public class Test {

	private EmployeeService employeeService;
	private String name;
	private List<EmployeePO> employeePOList;	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EmployeePO> getEmployeePOList() {
		return employeePOList;
	}
	public void setEmployeePOList(List<EmployeePO> employeePOList) {
		this.employeePOList = employeePOList;
	}
	
	
	public static void main(String[] args){
		//Test test=new Test();
		//test.employeePOList=test.employeeService.selectEmployeePOs();
		ApplicationContext context = new ClassPathXmlApplicationContext("appContext-action.xml","appContext-dao.xml","appContext-service.xml");
		System.out.println(context);
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		System.out.println(employeeService);
		List<EmployeePO> list=new ArrayList<EmployeePO>();
		
//		EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl();
		System.out.println("欢迎光临");
		EmployeePO employeePO=new EmployeePO();
		//Page<EmployeePO> pageBean=new Page<EmployeePO>(10, 3, 1);
		int page=1;
		int pageSize=3;
		employeePO.setSex("男");
		employeePO.setDepartment("软件部");
		employeePO.setEmployeeName("小明");
		Page<EmployeePO> pageBean=employeeService.selectEmployeePOList(employeePO, page, pageSize);
		list=pageBean.getCurPageData();
		for (EmployeePO po : list) {
			System.out.print(po.getEmployeeId()+"\t");
			System.out.print(po.getEmployeeName()+"\t");
			System.out.print(po.getSex()+"\t");
			System.out.print(po.getDepartment()+"\t");
			System.out.println(po.getSalary()+"\t");
		}
	}
}
