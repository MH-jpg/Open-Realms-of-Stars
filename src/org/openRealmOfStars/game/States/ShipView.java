package org.openRealmOfStars.game.States;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.openRealmOfStars.game.GameCommands;
import org.openRealmOfStars.gui.GuiStatics;
import org.openRealmOfStars.gui.ListRenderers.ShipStatRenderer;
import org.openRealmOfStars.gui.buttons.SpaceButton;
import org.openRealmOfStars.gui.infopanel.InfoPanel;
import org.openRealmOfStars.gui.labels.BaseInfoTextArea;
import org.openRealmOfStars.gui.labels.ImageLabel;
import org.openRealmOfStars.gui.panels.BlackPanel;
import org.openRealmOfStars.gui.panels.InvisiblePanel;
import org.openRealmOfStars.player.PlayerInfo;
import org.openRealmOfStars.player.ship.ShipDesign;
import org.openRealmOfStars.player.ship.ShipImage;
import org.openRealmOfStars.player.ship.ShipImages;
import org.openRealmOfStars.player.ship.ShipStat;

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
 * Ship view for showing ship design and stats
 * 
 */

public class ShipView extends BlackPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Player Info
   */
  private PlayerInfo player;
  
  /**
   * List of ship design
   */
  private JList<ShipStat> shipList;
  
  /**
   * Ship image label
   */
  private ImageLabel shipImage;
  
  /**
   * Text is containing information about the ship design and stats
   */
  private BaseInfoTextArea infoText;
  
  /**
   * Copy button was clicked
   */
  private boolean copyClicked;

  public ShipView(PlayerInfo player, ActionListener listener) {
    this.player = player;
    this.copyClicked = false;
    this.setLayout(new BorderLayout());
    InfoPanel base = new InfoPanel();
    base.setLayout(new BorderLayout());
    base.setTitle("Ships");
    shipList = new JList<>();
    shipList.setCellRenderer(new ShipStatRenderer());
    shipList.setListData(this.player.getShipStatList());
    shipList.setBackground(Color.BLACK);
    shipList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scroll = new JScrollPane(shipList);
    InvisiblePanel invis = new InvisiblePanel(base);
    invis.setLayout(new BoxLayout(invis, BoxLayout.Y_AXIS));
    invis.add(scroll);
    SpaceButton btn = new SpaceButton("Copy design", GameCommands.COMMAND_COPY_SHIP);
    btn.addActionListener(listener);
    invis.add(btn);
    btn = new SpaceButton("New design", GameCommands.COMMAND_SHIPDESIGN);
    btn.addActionListener(listener);
    invis.add(btn);
    base.add(invis,BorderLayout.WEST);

    invis = new InvisiblePanel(base);
    invis.setLayout(new BoxLayout(invis, BoxLayout.Y_AXIS));
    shipImage = new ImageLabel(ShipImages.Humans().getShipImage(ShipImage.SCOUT), true);
    shipImage.setFillColor(Color.BLACK);
    invis.add(shipImage);
    invis.add(Box.createRigidArea(new Dimension(5,5)));
    infoText = new BaseInfoTextArea(30, 30);
    infoText.setEditable(false);
    infoText.setFont(GuiStatics.getFontCubellanSmaller());
    scroll = new JScrollPane(infoText);
    invis.add(scroll);
    base.add(invis,BorderLayout.CENTER);

    
    this.add(base, BorderLayout.CENTER);
    

    // Bottom panel
    InfoPanel bottomPanel = new InfoPanel();
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.setTitle(null);
    btn = new SpaceButton("Back to star map", 
        GameCommands.COMMAND_VIEW_STARMAP);
    btn.addActionListener(listener);
    bottomPanel.add(btn,BorderLayout.CENTER);
    
    //updatePanel();
    // Add panels to base
    this.add(bottomPanel,BorderLayout.SOUTH);

  }

  /**
   * Handle actions for ship view.
   * @param arg0 ActionEvent command what player did
   */
  public void handleAction(ActionEvent arg0) {
    if (arg0.getActionCommand().equals(GameCommands.COMMAND_ANIMATION_TIMER)) {
      if (shipList.getSelectedIndex() != -1) {
        ShipStat stat = shipList.getSelectedValue();
        infoText.setText(stat.toString());
        shipImage.setImage(stat.getDesign().getHull().getImage());
        this.repaint();
      } else {
        infoText.setText("");
        this.repaint();
        shipImage.setImage(ShipImages.Humans().getShipImage(ShipImage.COLONY));
      }
    }
  }
  
  /**
   * Get Ship design which is selected
   * @return Get selected ship design or null
   */
  public ShipDesign getSelectedShip() {
    if (shipList.getSelectedIndex() != -1) {
      ShipStat stat = shipList.getSelectedValue();
      return stat.getDesign();
    }
    return null;
  }

  public boolean isCopyClicked() {
    return copyClicked;
  }

  public void setCopyClicked(boolean copyClicked) {
    this.copyClicked = copyClicked;
  }
 
}
