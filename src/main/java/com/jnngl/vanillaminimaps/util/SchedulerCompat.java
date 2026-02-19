/*
 *  Copyright (C) 2024-2026  JNNGL
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jnngl.vanillaminimaps.util;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public final class SchedulerCompat {

  private static final Runnable NO_OP_RETIRED = () -> {
  };

  private SchedulerCompat() {
  }

  public static void runEntity(Entity entity, Plugin plugin, Runnable task) {
    entity.getScheduler().run(plugin, scheduledTask -> task.run(), NO_OP_RETIRED);
  }

  public static void runEntityDelayed(Entity entity, Plugin plugin, Runnable task, long delayTicks) {
    entity.getScheduler().runDelayed(plugin, scheduledTask -> task.run(), NO_OP_RETIRED, delayTicks);
  }

  public static ScheduledTask runEntityAtFixedRate(Entity entity, Plugin plugin, Consumer<ScheduledTask> task,
                                                    Runnable retired, long initialDelayTicks, long periodTicks) {
    return entity.getScheduler().runAtFixedRate(plugin, task, retired, initialDelayTicks, periodTicks);
  }

  public static void runRegionDelayed(Plugin plugin, Location location, Runnable task, long delayTicks) {
    Bukkit.getRegionScheduler().runDelayed(plugin, location, scheduledTask -> task.run(), delayTicks);
  }
}
