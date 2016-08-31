package org.openRealmOfStars.AI.Mission;
/**
 * 
 * Open Realm of Stars game project
 * Copyright (C) 2016  Tuomo Untinen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/
 * 
 * 
 * Mission types for AI
 * 
 */

public enum MissionType {
  
  /**
   * Explore new unknown space and boldly go where no one
   * has gone before.
   */
  EXPLORE,
  /**
   * Colonize uncolonized planet
   */
  COLONIZE,
  /**
   * Defend planet
   */
  DEFEND;
  
  
  /**
   * Get Mission type with index
   * @return index
   */
  public int getIndex() {
    switch (this) {
    case EXPLORE: return 0;
    case COLONIZE: return 1;
    case DEFEND: return 2;
    }
    return 0;
  }
  
  /**
   * Get mission type by index
   * @param index
   * @return Mission Type, never null
   */
  public static MissionType getType(int index) {
    switch (index) {
    case 0: return MissionType.EXPLORE;
    case 1: return MissionType.COLONIZE;
    case 2: return MissionType.DEFEND;
    }
    return MissionType.EXPLORE;
  }

  @Override
  public String toString() {
    switch (this) {
    case EXPLORE: return "Explore";
    case COLONIZE: return "Colonize";
    case DEFEND: return "Defend";
    }
    return "Unknown";
  }
  
  
}
