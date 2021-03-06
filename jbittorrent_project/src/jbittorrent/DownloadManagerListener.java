/*
 * jbittorrent library is an implementation in Java language of BiTorrent protocol.
 *
 * It is based on the Java Bittorrent API of Baptiste Dubuis, Artificial Inteligency Laboratory, EPFL.
 * @version 1.0
 * @author Baptiste Dubuis
 * To contact the author:
 * email: baptiste.dubuis@gmail.com
 *
 * More information about Java Bittorrent API:
 * http://sourceforge.net/projects/bitext/
 *
 * New contribution are:
 * 1. Optimization of process establishement of conecctions betwen the peers of the swarm.
 * 2. Improvements in the Choking Algorithm.
 * 3. Improvements in the Optimistic Unchoking implementation.
 * 4. Implementation of Rarest First algorithm .
 * 5. Implementation of End Game Strategy.
 *
 * This project contains three packs:
 * 1. jbittorrent is the "client" part, i.e. it implements all classes needed to publish files, share them and download them.
 * 2. trackerBT is the "tracker" part, i.e. it implements all classes needed to run a Bittorrent tracker that coordinates peers exchanges.
 * 3. test contains example classes on how a developer could create new applications and new .torrent file.
 *
 * Copyright (C) 2013 Sandra Ferrer, AST Research Group
 *
 * jbittorrent is free software; you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published 
 * by the Free Software Foundation; either version 2 of the License, 
 * or (at your option) any later version.
 *
 * jbittorrent is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 *  @version 1.0
 *  @author Sandra Ferrer Celma <sandra.ferrer@urv.cat>
 * 
 */

package jbittorrent;

import java.util.EventListener;
import java.util.Map;

/**
 * Listener that manages the events of download finish and the events of swarm updating.
 * @author Sandra Ferrer Celma
 * @version 0.1
 *
 */
public interface DownloadManagerListener extends EventListener{
	
	/**
	 * Manage download completion event.
	 */
	public void downloadComplete();
	
	/**
	 * Updates the state of the peers in the swarm, according to the following parameters:
	 * <ul>
	 * <li>mapState is a map with the keys, <b>complete</b> the number of peers that are 
	 * seed and <b>incomplete<b> the number of peers that are downloading the file. </li>
	 * <li>lastTimeSendPieceBlock is the last time that the peer sent a block to other peer. </li>
	 * <li>lastTimeReceivePieceBlock : is the last time that the peer 
	 * received a block from other peer.</li>
	 * <li>initTimeStartedProtocol is the moment when starts the process. </li>
	 * </ul>
	 * @param mapState Map<String, Integer>
	 * @param lastTimeSendPieceBlock long
	 * @param lastTimeReceivePieceBlock long 
	 * @param initTimeStartedProtocol long
	 */
	public void updatePeerListState(Map<String, Integer> mapState, long lastTimeSendPieceBlock, long lastTimeReceivePieceBlock, long initTimeStartedProtocol);
}
