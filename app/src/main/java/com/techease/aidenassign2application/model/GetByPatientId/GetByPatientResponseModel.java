
package com.techease.aidenassign2application.model.GetByPatientId;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetByPatientResponseModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<GetByPatientDataMoldel> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<GetByPatientDataMoldel> getData() {
        return data;
    }

    public void setData(List<GetByPatientDataMoldel> data) {
        this.data = data;
    }

}
