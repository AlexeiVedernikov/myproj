package com.my3o.frontend.web.security;

import com.my3o.frontend.constant.My3oURL;

/**
 * Created by: Alexander Duckardt Date: 8/2/14.
 */
public class Privilege implements Cloneable {
    private String URL;
    private boolean isOpen = false;
    private boolean canWrite = false;

    private Privilege(String URL, boolean canWrite, boolean isOpen) {
        this.URL = URL;
        this.isOpen = isOpen;
        this.canWrite = canWrite;
    }

    private static Privilege getPublicPrivilege(String URL) {
        return new Privilege(URL, true, true);
    }

    private static Privilege getReadOnlyPrivilege(String URL) {
        return new Privilege(URL, false, false);
    }

    private static Privilege getFullPrivilege(String URL) {
        return new Privilege(URL, true, false);
    }

    public static final Privilege COUNTRY_LIST = Privilege.getReadOnlyPrivilege(My3oURL.COUNTRY_LIST);
    public static final Privilege COUNTRY_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.COUNTRY);
    public static final Privilege COUNTRY_EDIT = Privilege.getFullPrivilege(My3oURL.COUNTRY);

    public static final Privilege REGION_LIST = Privilege.getReadOnlyPrivilege(My3oURL.REGION_LIST);
    public static final Privilege REGION_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.REGION);
    public static final Privilege REGION_EDIT = Privilege.getFullPrivilege(My3oURL.REGION);
    public static final Privilege REGION_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.REGION_SEARCH);

    public static final Privilege TOWN_LIST = Privilege.getReadOnlyPrivilege(My3oURL.TOWN_LIST);
    public static final Privilege TOWN_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.TOWN);
    public static final Privilege TOWN_EDIT = Privilege.getFullPrivilege(My3oURL.TOWN);
    public static final Privilege TOWNS_SEARCH_REG = Privilege.getReadOnlyPrivilege(My3oURL.TOWN_SEARCH);

    public static final Privilege DISTRICT_LIST = Privilege.getReadOnlyPrivilege(My3oURL.DISTRICT_LIST);
    public static final Privilege DISTRICT_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.DISTRICT);
    public static final Privilege DISTRICT_EDIT = Privilege.getFullPrivilege(My3oURL.DISTRICT);
    public static final Privilege DISTRICT_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.DISTRICT_SEARCH);

    public static final Privilege TOWN_AREA_LIST = Privilege.getReadOnlyPrivilege(My3oURL.TOWN_AREA_LIST);
    public static final Privilege TOWN_AREA_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.TOWN_AREA);
    public static final Privilege TOWN_AREA_EDIT = Privilege.getFullPrivilege(My3oURL.TOWN_AREA);
    public static final Privilege TOWN_AREA_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.TOWN_AREA_SEARCH);

    public static final Privilege USER_LIST = Privilege.getReadOnlyPrivilege(My3oURL.USER_LIST);
    public static final Privilege USER_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.USER);
    public static final Privilege USER_EDIT = Privilege.getFullPrivilege(My3oURL.USER);
    public static final Privilege USER_SEARCH = Privilege.getFullPrivilege(My3oURL.USER_SEARCH);
    public static final Privilege USER_PASSWORD = Privilege.getFullPrivilege(My3oURL.USER_PASSWORD);
    // TODO: REFACTOR - NEED TO MAKE AJAX CALL FOR SEARCH INSREAD OF SEPARATE
    // URL.
    public static final Privilege ADD_PARENT = Privilege.getFullPrivilege(My3oURL.ADD_PARENT);
    public static final Privilege ADD_CHILD = Privilege.getFullPrivilege(My3oURL.ADD_CHILD);
    public static final Privilege DROP_PARENT_OR_CHILD = Privilege.getFullPrivilege(My3oURL.DROP_PARENT_OR_CHILD);

    public static final Privilege PARENTS_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.PARENTS_SEARCH);
    public static final Privilege CHILDREN_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.CHILDREN_SEARCH);

    public static final Privilege USER_PROFILE_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.USER_PROFILE);
    public static final Privilege USER_PROFILE_EDIT = Privilege.getFullPrivilege(My3oURL.USER_PROFILE);

    public static final Privilege NOTIFICATION_LIST = Privilege.getReadOnlyPrivilege(My3oURL.NOTIFICATION_LIST);
    public static final Privilege NOTIFICATION_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.NOTIFICATION);
    public static final Privilege NOTIFICATION_EDIT = Privilege.getFullPrivilege(My3oURL.NOTIFICATION);
    public static final Privilege NOTIFICATION_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.NOTIFICATION_SEARCH);
    public static final Privilege PROGRESS_NOTIFICATION_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.PROGRESS_NOTIFICATION_SEARCH);

    public static final Privilege SETTING_MAIL_LIST = Privilege.getReadOnlyPrivilege(My3oURL.SETTING_MAIL_LIST);
    public static final Privilege SETTING_MAIL_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.SETTING_MAIL);
    public static final Privilege SETTING_MAIL_EDIT = Privilege.getFullPrivilege(My3oURL.SETTING_MAIL);
    public static final Privilege SETTING_MAIL_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.SETTING_MAIL_SEARCH);

    public static final Privilege GROUP_LIST = Privilege.getReadOnlyPrivilege(My3oURL.GROUP_LIST);
    public static final Privilege GROUP_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.GROUP);
    public static final Privilege GROUP_EDIT = Privilege.getFullPrivilege(My3oURL.GROUP);
    public static final Privilege GROUP_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.GROUP_SEARCH);

    public static final Privilege LOGIN = Privilege.getPublicPrivilege(My3oURL.LOGIN);
    public static final Privilege LOGOUT = Privilege.getPublicPrivilege(My3oURL.LOGOUT);
    public static final Privilege QIWI_LIST = Privilege.getPublicPrivilege(My3oURL.QIWI_LIST);
    public static final Privilege TERMS_LIST = Privilege.getPublicPrivilege(My3oURL.TERMS_LIST);
    public static final Privilege INSTRUCTIONS_LIST = Privilege.getPublicPrivilege(My3oURL.INSTRUCTIONS_LIST);
    public static final Privilege CONTACTS = Privilege.getPublicPrivilege(My3oURL.CONTACTS);
    public static final Privilege SERVICES_AMOUNT = Privilege.getPublicPrivilege(My3oURL.SERVICES_AMOUNT);

    public static final Privilege BLOCK_LIST = Privilege.getReadOnlyPrivilege(My3oURL.BLOCK_LIST);

    public static final Privilege START = Privilege.getReadOnlyPrivilege(My3oURL.START);
    public static final Privilege INDEX = Privilege.getReadOnlyPrivilege(My3oURL.INDEX);

    public static final Privilege ORGANIZATION_LIST = Privilege.getReadOnlyPrivilege(My3oURL.ORGANIZATION_LIST);
    public static final Privilege ORGANIZATION_VIEW = Privilege.getReadOnlyPrivilege(My3oURL.ORGANIZATION_VIEW);
    public static final Privilege ORGANIZATION_EDIT = Privilege.getFullPrivilege(My3oURL.ORGANIZATION);
    public static final Privilege ORGANIZATION_SELECT = Privilege.getFullPrivilege(My3oURL.ORGANIZATION_SELECT);

    public static final Privilege BILL_USER_LIST = Privilege.getReadOnlyPrivilege(My3oURL.BILL_USER_LIST);
    public static final Privilege BILL_USER_LIST_MORE = Privilege.getReadOnlyPrivilege(My3oURL.BILL_USER_LIST_MORE);
    public static final Privilege NOTIFICATION_USER_LIST = Privilege.getReadOnlyPrivilege(My3oURL.NOTIFICATION_USER_LIST);

    public static final Privilege SHEDULES_LIST = Privilege.getReadOnlyPrivilege(My3oURL.SHEDULES_LIST);
    public static final Privilege VISITS_LIST = Privilege.getReadOnlyPrivilege(My3oURL.VISITS_LIST);
    public static final Privilege MARKS_LIST = Privilege.getReadOnlyPrivilege(My3oURL.MARKS_LIST);
    public static final Privilege SCHOOLS_LIST = Privilege.getReadOnlyPrivilege(My3oURL.SCHOOLS_LIST);
    public static final Privilege CLASS_LIST = Privilege.getReadOnlyPrivilege(My3oURL.CLASS_LIST);
    public static final Privilege ROOMS_LIST = Privilege.getReadOnlyPrivilege(My3oURL.ROOMS_LIST);
    public static final Privilege PLANS_LIST = Privilege.getReadOnlyPrivilege(My3oURL.PLANS_LIST);
    public static final Privilege DISCIPLINES_LIST = Privilege.getReadOnlyPrivilege(My3oURL.DISCIPLINES_LIST);
    public static final Privilege DISCIPLINES_EDIT = Privilege.getFullPrivilege(My3oURL.DISCIPLINES_EDIT);
    public static final Privilege DISCIPLINES_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.DISCIPLINES_SEARCH);

    public static final Privilege MARKS_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.MARKS_SEARCH);

    public static final Privilege TABLE = Privilege.getFullPrivilege(My3oURL.TABLE);

    public static final Privilege SHEDULE_LIST = Privilege.getReadOnlyPrivilege(My3oURL.SHEDULE_LIST);
    public static final Privilege SHEDULE_EDIT = Privilege.getFullPrivilege(My3oURL.SHEDULE);
    public static final Privilege SHEDULE_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.SHEDULE_SEARCH);

    public static final Privilege JOURNAL_LIST = Privilege.getReadOnlyPrivilege(My3oURL.JOURNAL_LIST);
    public static final Privilege JOURNAL_EDIT  = Privilege.getFullPrivilege(My3oURL.JOURNAL);
    public static final Privilege JOURNAL_SEARCH = Privilege.getReadOnlyPrivilege(My3oURL.JOURNAL_SEARCH);

    public static final Privilege PAID_GROUPS = Privilege.getReadOnlyPrivilege(My3oURL.PAID_GROUPS);

    public String getURL() {
        return URL;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    @Override
    public Privilege clone() throws CloneNotSupportedException {
        return new Privilege(this.URL, this.canWrite, this.isOpen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Privilege privilege = (Privilege) o;

        if (canWrite != privilege.canWrite)
            return false;
        if (isOpen != privilege.isOpen)
            return false;
        if (URL != null ? !URL.equals(privilege.URL) : privilege.URL != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = URL != null ? URL.hashCode() : 0;
        result = 31 * result + (isOpen ? 1 : 0);
        result = 31 * result + (canWrite ? 1 : 0);
        return result;
    }
}
