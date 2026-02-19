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

package com.jnngl.vanillaminimaps.clientside.impl;

import java.util.function.Predicate;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import org.jspecify.annotations.NullMarked;

@NullMarked
final class NoOpEntitySynchronizer implements ServerEntity.Synchronizer {

  static final NoOpEntitySynchronizer INSTANCE = new NoOpEntitySynchronizer();

  private NoOpEntitySynchronizer() {
  }

  @Override
  public void sendToTrackingPlayers(Packet<? super ClientGamePacketListener> packet) {
  }

  @Override
  public void sendToTrackingPlayersAndSelf(Packet<? super ClientGamePacketListener> packet) {
  }

  @Override
  public void sendToTrackingPlayersFiltered(Packet<? super ClientGamePacketListener> packet, Predicate<ServerPlayer> predicate) {
  }
}
