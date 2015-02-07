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
            if (o1.getTromitBills() == null && o2.getTromitBills() == null) {
                return 0;
            }
            if (o1.getTromitBills() == null) {
                return -1;
            }
            if (o2.getTromitBills() == null) {
                return 1;
            }
            return o1.getTromitBills().compareTo(o2.getTromitBills());
        }
    }

    public static final class BillsProposedGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getProposedBills() == null && o2.getProposedBills() == null) {
                return 0;
            }
            if (o1.getProposedBills() == null) {
                return -1;
            }
            if (o2.getProposedBills() == null) {
                return 1;
            }
            return o1.getProposedBills().compareTo(o2.getProposedBills());
        }
    }
    
    public static final class BillsApprovedGovMemberComparator implements Comparator<GovMember> {
        @Override
        public int compare(GovMember o1, GovMember o2) {
            if (o1.getApprovedBills() == null && o2.getApprovedBills() == null) {
                return 0;
            }
            if (o1.getApprovedBills() == null) {
                return -1;
            }
            if (o2.getApprovedBills() == null) {
                return 1;
            }
            return o1.getApprovedBills().compareTo(o2.getApprovedBills());
        }
    }
    
}
