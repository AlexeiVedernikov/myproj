package com.my3o.frontend.web.security;

import com.my3o.common.constant.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by: Alexander Duckardt
 * Date: 8/2/14.
 */
public class My3oPermissions implements Cloneable {
    private final static Logger log = LoggerFactory.getLogger(My3oPermissions.class);
    public static My3oPermissions[] userPermissions;

    private Set<Privilege> privileges;

    static {
        userPermissions = new My3oPermissions[UserType.values().length];
        for(UserType role: UserType.values()){
            userPermissions[role.ordinal()] = new My3oPermissions();
            userPermissions[role.ordinal()].privileges = new HashSet<>();

            switch (role){
                case SuperAdmin:
                    break;
                case ResponsibleForPayment:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                            .addPrivileges(Privilege.USER_SEARCH)
                            .addPrivileges(Privilege.CHILDREN_SEARCH)
                            .addPrivileges(Privilege.PARENTS_SEARCH)
                            .addPrivileges(Privilege.USER_PROFILE_EDIT)
                            .addPrivileges(Privilege.GROUP_LIST)
                            .addPrivileges(Privilege.GROUP_SEARCH)
                            .addPrivileges(Privilege.START)
                            .addPrivileges(Privilege.INDEX)
                            .addPrivileges(Privilege.ORGANIZATION_LIST)
                            .addPrivileges(Privilege.ORGANIZATION_SELECT)
                            .addPrivileges(Privilege.SHEDULES_LIST)
                            .addPrivileges(Privilege.SHEDULE_SEARCH)
                            .addPrivileges(Privilege.JOURNAL_LIST)
                            .addPrivileges(Privilege.JOURNAL_SEARCH)
                            .addPrivileges(Privilege.VISITS_LIST)
                            .addPrivileges(Privilege.CLASS_LIST)
                            .addPrivileges(Privilege.ROOMS_LIST)
                            .addPrivileges(Privilege.PLANS_LIST)
                            .addPrivileges(Privilege.TABLE)
                            .addPrivileges(Privilege.DISCIPLINES_LIST)
                            .addPrivileges(Privilege.DISCIPLINES_SEARCH)
                            .addPrivileges(Privilege.PAID_GROUPS);
                    break;
                case SchoolAdmin:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                                                   .addPrivileges(Privilege.USER_EDIT)
                                                   .addPrivileges(Privilege.USER_SEARCH)
                                                   .addPrivileges(Privilege.USER_PASSWORD)
                                                   .addPrivileges(Privilege.ADD_CHILD)
                                                   .addPrivileges(Privilege.ADD_PARENT)
                                                   .addPrivileges(Privilege.CHILDREN_SEARCH)
                                                   .addPrivileges(Privilege.PARENTS_SEARCH)
                                                   .addPrivileges(Privilege.DROP_PARENT_OR_CHILD)
                                                   .addPrivileges(Privilege.USER_PROFILE_EDIT)
                                                   .addPrivileges(Privilege.GROUP_LIST)
                                                   .addPrivileges(Privilege.GROUP_EDIT)
                                                   .addPrivileges(Privilege.GROUP_SEARCH)
                                                   .addPrivileges(Privilege.NOTIFICATION_LIST)
                                                   .addPrivileges(Privilege.NOTIFICATION_EDIT)
                                                   .addPrivileges(Privilege.NOTIFICATION_SEARCH)
                                                   .addPrivileges(Privilege.PROGRESS_NOTIFICATION_SEARCH)
                                                   .addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                   .addPrivileges(Privilege.ORGANIZATION_LIST)
                                                   .addPrivileges(Privilege.ORGANIZATION_SELECT)
                                                   .addPrivileges(Privilege.SETTING_MAIL_LIST)
                                                   .addPrivileges(Privilege.SETTING_MAIL_EDIT)
                                                   .addPrivileges(Privilege.SHEDULES_LIST)
                                                   .addPrivileges(Privilege.SHEDULE_LIST)
                                                   .addPrivileges(Privilege.SHEDULE_EDIT)
                                                   .addPrivileges(Privilege.SHEDULE_SEARCH)
                                                   .addPrivileges(Privilege.JOURNAL_LIST)
                                                   .addPrivileges(Privilege.JOURNAL_EDIT)
                                                   .addPrivileges(Privilege.JOURNAL_SEARCH)
                                                   .addPrivileges(Privilege.VISITS_LIST)
                                                   .addPrivileges(Privilege.MARKS_LIST)
                                                   .addPrivileges(Privilege.CLASS_LIST)
                                                   .addPrivileges(Privilege.ROOMS_LIST)
                                                   .addPrivileges(Privilege.PLANS_LIST)
                                                   .addPrivileges(Privilege.TABLE)
                                                   .addPrivileges(Privilege.DISCIPLINES_LIST)
                                                   .addPrivileges(Privilege.DISCIPLINES_EDIT)
                                                   .addPrivileges(Privilege.DISCIPLINES_SEARCH)
                                                    .addPrivileges(Privilege.MARKS_SEARCH);
                    break;
                case Onlooker:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                                                    .addPrivileges(Privilege.USER_SEARCH)
                                                    .addPrivileges(Privilege.CHILDREN_SEARCH)
                                                    .addPrivileges(Privilege.PARENTS_SEARCH)
                                                    .addPrivileges(Privilege.USER_PROFILE_EDIT)
                                                    .addPrivileges(Privilege.GROUP_LIST)
                                                    .addPrivileges(Privilege.GROUP_SEARCH)
                                                    .addPrivileges(Privilege.START)
                                                    .addPrivileges(Privilege.INDEX)
                                                    .addPrivileges(Privilege.ORGANIZATION_LIST)
                                                    .addPrivileges(Privilege.ORGANIZATION_SELECT)
                                                    .addPrivileges(Privilege.SHEDULES_LIST)
                                                    .addPrivileges(Privilege.SHEDULE_LIST)
                                                    .addPrivileges(Privilege.SHEDULE_SEARCH)
                                                    .addPrivileges(Privilege.JOURNAL_LIST)
                                                    .addPrivileges(Privilege.JOURNAL_SEARCH)
                                                    .addPrivileges(Privilege.VISITS_LIST)
                                                    .addPrivileges(Privilege.MARKS_LIST)
                                                    .addPrivileges(Privilege.CLASS_LIST)
                                                    .addPrivileges(Privilege.ROOMS_LIST)
                                                    .addPrivileges(Privilege.PLANS_LIST)
                                                    .addPrivileges(Privilege.TABLE)
                                                    .addPrivileges(Privilege.DISCIPLINES_LIST)
                                                    .addPrivileges(Privilege.DISCIPLINES_SEARCH)
                                                    .addPrivileges(Privilege.MARKS_SEARCH);
                    break;
                case RegistrarAccounts:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                                                    .addPrivileges(Privilege.USER_EDIT)
                                                    .addPrivileges(Privilege.USER_SEARCH)
                                                    .addPrivileges(Privilege.USER_PASSWORD)
                                                    .addPrivileges(Privilege.ADD_CHILD)
                                                    .addPrivileges(Privilege.ADD_PARENT)
                                                    .addPrivileges(Privilege.CHILDREN_SEARCH)
                                                    .addPrivileges(Privilege.PARENTS_SEARCH)
                                                    .addPrivileges(Privilege.DROP_PARENT_OR_CHILD)
                                                    .addPrivileges(Privilege.USER_PROFILE_EDIT)
                                                    .addPrivileges(Privilege.GROUP_LIST)
                                                    .addPrivileges(Privilege.GROUP_EDIT)
                                                    .addPrivileges(Privilege.GROUP_SEARCH)
                                                    .addPrivileges(Privilege.START)
                                                    .addPrivileges(Privilege.INDEX)
                                                    .addPrivileges(Privilege.ORGANIZATION_LIST)
                                                    .addPrivileges(Privilege.ORGANIZATION_SELECT)
                                                    .addPrivileges(Privilege.ORGANIZATION_EDIT)
                                                    .addPrivileges(Privilege.ORGANIZATION_VIEW)
                                                    .addPrivileges(Privilege.SHEDULES_LIST)
                                                    .addPrivileges(Privilege.SHEDULE_LIST)
                                                    .addPrivileges(Privilege.SHEDULE_EDIT)
                                                    .addPrivileges(Privilege.SHEDULE_SEARCH)
                                                    .addPrivileges(Privilege.JOURNAL_LIST)
                                                    .addPrivileges(Privilege.JOURNAL_SEARCH)
                                                    .addPrivileges(Privilege.VISITS_LIST)
                                                    .addPrivileges(Privilege.MARKS_LIST)
                                                    .addPrivileges(Privilege.CLASS_LIST)
                                                    .addPrivileges(Privilege.ROOMS_LIST)
                                                    .addPrivileges(Privilege.PLANS_LIST)
                                                    .addPrivileges(Privilege.TABLE)
                                                    .addPrivileges(Privilege.DISCIPLINES_LIST)
                                                    .addPrivileges(Privilege.DISCIPLINES_EDIT)
                                                    .addPrivileges(Privilege.DISCIPLINES_SEARCH)
                                                    .addPrivileges(Privilege.MARKS_SEARCH)
                                                    .addPrivileges(Privilege.COUNTRY_LIST)
                                                    .addPrivileges(Privilege.COUNTRY_EDIT)
                                                    .addPrivileges(Privilege.REGION_LIST)
                                                    .addPrivileges(Privilege.REGION_EDIT)
                                                    .addPrivileges(Privilege.REGION_SEARCH)
                                                    .addPrivileges(Privilege.TOWN_LIST)
                                                    .addPrivileges(Privilege.TOWN_EDIT)
                                                    .addPrivileges(Privilege.TOWNS_SEARCH_REG)
                                                    .addPrivileges(Privilege.DISTRICT_LIST)
                                                    .addPrivileges(Privilege.DISTRICT_EDIT)
                                                    .addPrivileges(Privilege.DISTRICT_SEARCH)
                                                    .addPrivileges(Privilege.TOWN_AREA_LIST)
                                                    .addPrivileges(Privilege.TOWN_AREA_EDIT)
                                                    .addPrivileges(Privilege.TOWN_AREA_SEARCH);
                    break;
                case OperatorMarks:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                                                    .addPrivileges(Privilege.USER_SEARCH)
                                                    .addPrivileges(Privilege.USER_PROFILE_EDIT)
                                                    .addPrivileges(Privilege.GROUP_LIST)
                                                    .addPrivileges(Privilege.GROUP_SEARCH)
                                                    .addPrivileges(Privilege.NOTIFICATION_LIST)
                                                    .addPrivileges(Privilege.NOTIFICATION_SEARCH)
                                                    .addPrivileges(Privilege.START)
                                                    .addPrivileges(Privilege.INDEX)
                                                    .addPrivileges(Privilege.ORGANIZATION_LIST)
                                                    .addPrivileges(Privilege.ORGANIZATION_SELECT)
                                                    .addPrivileges(Privilege.SHEDULES_LIST)
                                                    .addPrivileges(Privilege.SHEDULE_SEARCH)
                                                    .addPrivileges(Privilege.JOURNAL_LIST)
                                                    .addPrivileges(Privilege.JOURNAL_EDIT)
                                                    .addPrivileges(Privilege.JOURNAL_SEARCH)
                                                    .addPrivileges(Privilege.VISITS_LIST)
                                                    .addPrivileges(Privilege.MARKS_LIST)
                                                    .addPrivileges(Privilege.CLASS_LIST)
                                                    .addPrivileges(Privilege.ROOMS_LIST)
                                                    .addPrivileges(Privilege.PLANS_LIST)
                                                    .addPrivileges(Privilege.TABLE)
                                                    .addPrivileges(Privilege.DISCIPLINES_LIST)
                                                    .addPrivileges(Privilege.DISCIPLINES_SEARCH)
                                                    .addPrivileges(Privilege.MARKS_SEARCH);
                    break;
                case Teacher:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.USER_LIST)
                                                    .addPrivileges(Privilege.USER_SEARCH)
                                                    .addPrivileges(Privilege.USER_PASSWORD)
                                                   .addPrivileges(Privilege.CHILDREN_SEARCH)
                                                   .addPrivileges(Privilege.PARENTS_SEARCH)
                                                   .addPrivileges(Privilege.USER_PROFILE_VIEW)
                                                   .addPrivileges(Privilege.GROUP_LIST)
                                                   .addPrivileges(Privilege.GROUP_VIEW)
                                                   .addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                   .addPrivileges(Privilege.ORGANIZATION_SELECT)
                                                   .addPrivileges(Privilege.SHEDULES_LIST)
                                                   .addPrivileges(Privilege.VISITS_LIST)
                                                   .addPrivileges(Privilege.MARKS_LIST)
                                                    .addPrivileges(Privilege.JOURNAL_LIST)
                                                    .addPrivileges(Privilege.JOURNAL_EDIT)
                                                    .addPrivileges(Privilege.JOURNAL_SEARCH)
                                                    .addPrivileges(Privilege.SHEDULE_LIST)
                                                    .addPrivileges(Privilege.SHEDULE_SEARCH)
                                                    .addPrivileges(Privilege.MARKS_SEARCH);

                    break;
                case Scholar:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.PARENTS_SEARCH)
//                                                   .addPrivileges(Privilege.USER_LIST)
                                                   .addPrivileges(Privilege.USER_PROFILE_VIEW)
                                                    .addPrivileges(Privilege.USER_PASSWORD)
//                                                   .addPrivileges(Privilege.GROUP_LIST)
                                                   .addPrivileges(Privilege.GROUP_VIEW)
                                                   .addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                   .addPrivileges(Privilege.BILL_USER_LIST)
                                                   .addPrivileges(Privilege.BILL_USER_LIST_MORE)
                                                   .addPrivileges(Privilege.NOTIFICATION_USER_LIST)
                                                   .addPrivileges(Privilege.SHEDULES_LIST)
                                                   .addPrivileges(Privilege.VISITS_LIST)
                                                   .addPrivileges(Privilege.BLOCK_LIST)
                                                   .addPrivileges(Privilege.MARKS_LIST)
                                                   .addPrivileges(Privilege.MARKS_SEARCH);
                    break;
                case Parent:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.CHILDREN_SEARCH)
                                                   .addPrivileges(Privilege.USER_LIST)
                                                   .addPrivileges(Privilege.USER_PASSWORD)
                                                   .addPrivileges(Privilege.USER_PROFILE_VIEW)
                                                   .addPrivileges(Privilege.GROUP_LIST)
                                                   .addPrivileges(Privilege.GROUP_VIEW)
                                                   .addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                   .addPrivileges(Privilege.BILL_USER_LIST)
                                                   .addPrivileges(Privilege.BILL_USER_LIST_MORE)
                                                   .addPrivileges(Privilege.NOTIFICATION_USER_LIST)
                                                   .addPrivileges(Privilege.SHEDULES_LIST)
                                                   .addPrivileges(Privilege.VISITS_LIST)
                                                   .addPrivileges(Privilege.BLOCK_LIST)
                                                   .addPrivileges(Privilege.MARKS_LIST)
                                                   .addPrivileges(Privilege.MARKS_SEARCH);
                    break;
                case Other:
                    userPermissions[role.ordinal()].addPrivileges(Privilege.START)
                                                   .addPrivileges(Privilege.INDEX)
                                                   .addPrivileges(Privilege.LOGIN)
                                                   .addPrivileges(Privilege.QIWI_LIST)
                                                   .addPrivileges(Privilege.TERMS_LIST)
                                                   .addPrivileges(Privilege.INSTRUCTIONS_LIST)
                                                   .addPrivileges(Privilege.CONTACTS)
                                                   .addPrivileges(Privilege.SERVICES_AMOUNT)
												   .addPrivileges(Privilege.USER_PASSWORD)
                                                   .addPrivileges(Privilege.LOGOUT);
                    break;
                default:
                    break;
            }
        }
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }
    public My3oPermissions addPrivileges(Privilege privilege) {
        if (this.privileges == null)
            this.privileges = new HashSet<>();
        this.privileges.add(privilege);
        return this;
    }

}
