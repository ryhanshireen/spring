/*package com.thbs.ms.im.training.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.thbs.ms.im.training.entity.EmployeeRequest;
import com.thbs.ms.im.training.entity.SampleRequest;
@Mapper
public interface SampleRequestMapper {
	
	SampleRequestMapper INSTANCE = Mappers.getMapper(SampleRequestMapper.class);

    @Mapping(source = "ename", target = "employeeName")
    @Mapping(source = "eid", target = "employeeId")
    EmployeeRequest mapToEmployeeRequest(SampleRequest sampleRequest);
}*/

package com.thbs.ms.im.training.mapper;
 
import com.thbs.ms.im.training.entity.SampleRequest;
 
public class SampleRequestMapper {
 
    public static SampleRequest mapRequest(String employeename, String employeeid) {
        SampleRequest sampleRequest = new SampleRequest();
        sampleRequest.setEname(employeename);
        sampleRequest.setEid(employeeid);
        return sampleRequest;
    }
}