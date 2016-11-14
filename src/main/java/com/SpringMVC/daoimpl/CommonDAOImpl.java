package com.SpringMVC.daoimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.SpringMVC.dao.CommonDAO;

@Repository 
public class CommonDAOImpl implements CommonDAO { 

    public List<String> periodList() {
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        List<String> periods = new ArrayList<String>();
        for(int m=currentmonth-3;m<=currentmonth+3;m++){
        	switch(m){
        		case -2:
	        	case -1:
	        	case 0:{
	        		periods.add(String.valueOf(currentyear-1)+"/"+String.valueOf(m+12));        		
	        		break;
	        	}
	        	case 1:
	        	case 2:
	        	case 3:
	        	case 4:
	        	case 5:
	        	case 6:
	        	case 7:
	        	case 8:
	        	case 9:
	        	case 10:
	        	case 11:
	        	case 12:{
	        		periods.add(String.valueOf(currentyear)+"/"+String.valueOf(m));        			        		
	        		break;
	        	}
	        	case 13:
	        	case 14:
	        	case 15:{
	        		periods.add(String.valueOf(currentyear+1)+"/"+String.valueOf(m-12));        			        		
	        		break;
	        	}	        		
        	}
        }
        return periods;
    }
}