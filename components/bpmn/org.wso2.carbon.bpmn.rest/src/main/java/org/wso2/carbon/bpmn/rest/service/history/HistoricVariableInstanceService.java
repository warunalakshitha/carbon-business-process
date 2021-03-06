/**
 *  Copyright (c) 2015 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.bpmn.rest.service.history;

import org.wso2.carbon.bpmn.rest.model.common.DataResponse;
import org.wso2.carbon.bpmn.rest.model.history.HistoricVariableInstanceQueryRequest;
import org.wso2.carbon.bpmn.rest.service.base.BaseHistoricVariableInstanceService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/historic-variable-instances")
public class HistoricVariableInstanceService extends BaseHistoricVariableInstanceService{

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getHistoricActivityInstances() {
        HistoricVariableInstanceQueryRequest query = new HistoricVariableInstanceQueryRequest();

        // Populate query based on request
        Map<String, String> allRequestParams = new HashMap<>();

        for (String property:allPropertiesList){
            String value= uriInfo.getQueryParameters().getFirst(property);

            if(value != null){
                allRequestParams.put(property, value);
            }
        }
        if (allRequestParams.get("excludeTaskVariables") != null) {
            query.setExcludeTaskVariables(Boolean.valueOf(allRequestParams.get("excludeTaskVariables")));
        }

        if (allRequestParams.get("taskId") != null) {
            query.setTaskId(allRequestParams.get("taskId"));
        }

        if(allRequestParams.get("executionId") != null)
        {
            query.setExecutionId(allRequestParams.get("executionId"));
        }

        if (allRequestParams.get("processInstanceId") != null) {
            query.setProcessInstanceId(allRequestParams.get("processInstanceId"));
        }

        if (allRequestParams.get("variableName") != null) {
            query.setVariableName(allRequestParams.get("variableName"));
        }

        if (allRequestParams.get("variableNameLike") != null) {
            query.setVariableNameLike(allRequestParams.get("variableNameLike"));
        }

        DataResponse dataResponse = getQueryResponse(query, allRequestParams);
        return Response.ok().entity(dataResponse).build();
    }


}
