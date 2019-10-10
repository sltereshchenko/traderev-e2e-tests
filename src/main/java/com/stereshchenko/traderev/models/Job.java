package com.stereshchenko.traderev.models;

public class Job {
    public final String title;
    public final String location;
    public final String team;
    public final String commitment;

    public Job(final String title, final String location, final String team, final String commitment) {
        this.title = title;
        this.location = location;
        this.team = team;
        this.commitment = commitment;
    }
}
