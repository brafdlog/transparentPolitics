package com.transparent.politics.services;

import java.util.Comparator;

import com.transparent.politics.services.data.GovMember;

public class GovMemberComparators {

    public static final class WeeklyPresenceGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getAverageWeeklyPresenceHours() == null && o2.getAverageWeeklyPresenceHours() == null) {
                return 0;
            }
            if (o1.getAverageWeeklyPresenceHours() == null) {
                return -1;
            }
            if (o2.getAverageWeeklyPresenceHours() == null) {
                return 1;
            }
            return o1.getAverageWeeklyPresenceHours().compareTo(o2.getAverageWeeklyPresenceHours());
        }
    }
    
    public static final class MonthlyCommitteePresenceGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getAverageMonthlyCommitteePresence() == null && o2.getAverageMonthlyCommitteePresence() == null) {
                return 0;
            }
            if (o1.getAverageMonthlyCommitteePresence() == null) {
                return -1;
            }
            if (o2.getAverageMonthlyCommitteePresence() == null) {
                return 1;
            }
            return o1.getAverageMonthlyCommitteePresence().compareTo(o2.getAverageMonthlyCommitteePresence());
        }
    }
    
    public static final class BillsPreGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getBillsStatsPre() == null && o2.getBillsStatsPre() == null) {
                return 0;
            }
            if (o1.getBillsStatsPre() == null) {
                return -1;
            }
            if (o2.getBillsStatsPre() == null) {
                return 1;
            }
            return o1.getBillsStatsPre().compareTo(o2.getBillsStatsPre());
        }
    }

    public static final class BillsProposedGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getBillsStatsProposed() == null && o2.getBillsStatsProposed() == null) {
                return 0;
            }
            if (o1.getBillsStatsProposed() == null) {
                return -1;
            }
            if (o2.getBillsStatsProposed() == null) {
                return 1;
            }
            return o1.getBillsStatsProposed().compareTo(o2.getBillsStatsProposed());
        }
    }
    
    public static final class BillsApprovedGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getBillsStatsApproved() == null && o2.getBillsStatsApproved() == null) {
                return 0;
            }
            if (o1.getBillsStatsApproved() == null) {
                return -1;
            }
            if (o2.getBillsStatsApproved() == null) {
                return 1;
            }
            return o1.getBillsStatsApproved().compareTo(o2.getBillsStatsApproved());
        }
    }
    
}
