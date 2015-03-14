package com.transparent.politics.services.data;

public class GovMemberAveragesRdt {

    private final double averageWeeklyPresenceHours;
    private final double averageMonthlyCommitteePresence;
    private final double averageProposedBills;
    private final double averageApprovedBills;

    public GovMemberAveragesRdt(double averageWeeklyPresenceHours, double averageMonthlyCommitteePresence, double averageProposedBills, double averageApprovedBills) {
        this.averageWeeklyPresenceHours = averageWeeklyPresenceHours;
        this.averageMonthlyCommitteePresence = averageMonthlyCommitteePresence;
        this.averageProposedBills = averageProposedBills;
        this.averageApprovedBills = averageApprovedBills;
    }

    public double getAverageWeeklyPresenceHours() {
        return averageWeeklyPresenceHours;
    }

    public double getAverageMonthlyCommitteePresence() {
        return averageMonthlyCommitteePresence;
    }

    public double getAverageProposedBills() {
        return averageProposedBills;
    }

    public double getAverageApprovedBills() {
        return averageApprovedBills;
    }

}
