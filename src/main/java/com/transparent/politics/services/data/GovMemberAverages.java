package com.transparent.politics.services.data;

public class GovMemberAverages {

    private final double averageWeeklyPresenceHours;
    private final double averageMonthlyCommitteePresence;
    private final double averageProposedBills;
    private final double averageApprovedBills;
    private final double averageGrade;

    public GovMemberAverages(double averageWeeklyPresenceHours, double averageMonthlyCommitteePresence, double averageProposedBills, double averageApprovedBills,
            double averageGrade) {
        this.averageWeeklyPresenceHours = averageWeeklyPresenceHours;
        this.averageMonthlyCommitteePresence = averageMonthlyCommitteePresence;
        this.averageProposedBills = averageProposedBills;
        this.averageApprovedBills = averageApprovedBills;
        this.averageGrade = averageGrade;
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

    public double getAverageGrade() {
        return averageGrade;
    }
    
}
