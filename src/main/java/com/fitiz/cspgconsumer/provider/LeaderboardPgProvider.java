package com.fitiz.cspgconsumer.provider;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.fitiz.cspgconsumer.tables.Leaderboard.LEADERBOARD;

@Repository
public class LeaderboardPgProvider {

    private final DSLContext dslContext;

    public LeaderboardPgProvider(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean updateStepCount(UUID userId, Integer steps) {
        int res = dslContext.update(LEADERBOARD)
                .set(LEADERBOARD.STEPS, LEADERBOARD.STEPS.plus(steps))
                .where(LEADERBOARD.PARTICIPANT_ID.eq(userId))
                .execute();
        return res != 0;
    }
}
