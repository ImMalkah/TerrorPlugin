package co.terrorsquadmc.terrorplugin.Utilities;

import java.util.Date;

public class PlayerStats {
    private final String uuid, name;
    private int kills;
    private int deaths;
    private long blocksBroken;
    private final Date firstLogin;
    private Date lastLogin, lastLogout;

    public PlayerStats(String uuid, String name, int kills, int deaths, long blocksBroken, Date firstLogin, Date lastLogin, Date lastLogout) {
        this.uuid = uuid;
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
        this.blocksBroken = blocksBroken;
        this.firstLogin = firstLogin;
        this.lastLogin = lastLogin;
        this.lastLogout = lastLogout;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public long getBlocksBroken() {
        return blocksBroken;
    }

    public void setBlocksBroken(long blocksBroken) {
        this.blocksBroken = blocksBroken;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Date lastLogout) {
        this.lastLogout = lastLogout;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }
}
