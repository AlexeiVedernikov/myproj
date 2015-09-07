package com.my3o.frontend.web.model;

import com.my3o.common.constant.ParentType;

/**
 * Created by chudoshilkin on 24.07.14.
 */
public class ParentChildWebModel extends AbstractDataModel {
    private String childId;
    private String parentId;
    private ParentType parentType;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ParentType getParentType() {
        return parentType;
    }

    public void setParentType(ParentType parentType) {
        this.parentType = parentType;
    }
}
