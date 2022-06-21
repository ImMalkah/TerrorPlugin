package co.terrorsquadmc.terrorplugin.SQL;

import java.util.Date;

public class PlayerStats {
    private String uuid, name;
    private int kills, deaths;
    private Date firstLogin;
    private long blocksBroken;

    public PlayerStats(String uuid, int kills, Date firstLogin, long blocksBroken, int deaths, String name) {
        this.uuid = uuid;
        this.kills = kills;
        this.firstLogin = firstLogin;
        this.blocksBroken = blocksBroken;
        this.deaths = deaths;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    public long getBlocksBroken() {
        return blocksBroken;
    }

    public void setBlocksBroken(long blocksBroken) {
        this.blocksBroken = blocksBroken;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
