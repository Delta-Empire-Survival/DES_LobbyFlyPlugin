package com.github.Shin_Ideal.LobbyFlyPlugin.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Horizon_Limit_Listener implements Listener {

    private static double x_min_distance;
    private static double x_max_distance;
    private static double z_min_distance;
    private static double z_max_distance;

    public static void setDistance(double xmin, double xman, double zmin, double zmax) {
        Horizon_Limit_Listener.x_min_distance = xmin;
        Horizon_Limit_Listener.x_max_distance = xman;
        Horizon_Limit_Listener.z_min_distance = zmin;
        Horizon_Limit_Listener.z_max_distance = zmax;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Vector player_vector = player.getVelocity();
        double x = player.getLocation().getX();
        double z = player.getLocation().getZ();

        if (x < x_min_distance) {
            if (x < x_min_distance - 100) {
                Location location = player.getLocation();
                location.setX(x_min_distance);
                player.teleport(location);
            }
            player.setVelocity(player_vector.add(new Vector(1, 0, 0)));
        }

        if (x_max_distance < x) {
            if (x_max_distance + 100 < x) {
                Location location = player.getLocation();
                location.setX(x_max_distance);
                player.teleport(location);
            }
            player.setVelocity(player_vector.add(new Vector(-1, 0, 0)));
        }

        if (z < z_min_distance) {
            if (z < z_min_distance - 100) {
                Location location = player.getLocation();
                location.setZ(z_min_distance);
                player.teleport(location);
            }
            player.setVelocity(player_vector.add(new Vector(0, 0, 1)));
        }

        if (z_max_distance < z) {
            if (z_max_distance + 100 < z) {
                Location location = player.getLocation();
                location.setZ(z_max_distance);
                player.teleport(location);
            }
            player.setVelocity(player_vector.add(new Vector(0, 0, -1)));
        }
    }

}
