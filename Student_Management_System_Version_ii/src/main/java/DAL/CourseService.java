package DAL;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sms.model.*;

public class CourseService {
		CRUD_Courses crud_courses = new CRUD_Courses();

	    public Page<tblCourses> findPaginated(Pageable pageable,List<tblCourses> temp_courses)
	    {
	    	
	        int pageSize = pageable.getPageSize();
	        int currentPage = pageable.getPageNumber();
	        int startItem = currentPage * pageSize;
	        List<tblCourses> list;
	 
	        if (temp_courses.size() < startItem) {
	            list = Collections.emptyList();
	        } else {
	            int toIndex = Math.min(startItem + pageSize, temp_courses.size());
	            list = temp_courses.subList(startItem, toIndex);
	        }
	 
	        Page<tblCourses> bookPage = new PageImpl<tblCourses>(list, PageRequest.of(currentPage, pageSize), temp_courses.size());
	 
	        return bookPage;
	    }
}
