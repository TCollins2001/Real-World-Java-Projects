package com.teonvioncollins.timestream.models;

import java.util.List;

public class InviteRequest {
    private List<String> invitees;

    public List<String> getInvitees() {
        return invitees;
    }

    public void setInvitees(List<String> invitees) {
        this.invitees = invitees;
    }
}

