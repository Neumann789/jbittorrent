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

public class Bits {
    private boolean[] bits;

    public Bits(int length){
        this.bits = new boolean[length];
    }
    public Bits(byte[] b) {
        this.bits = Utils.byteArray2BitArray(b);
    }
    public Bits(){}

    public Bits and(Bits b){
        if(this.length() != b.length()){
            System.err.println("Error during and operation: bits length doesn't match");
            return null;
        }
        Bits temp = new Bits(this.length());
        for(int i = 0; i < this.length(); i++)
            temp.set(i, this.get(i) && b.get(i));
        return temp;
    }

    public Bits or(Bits b){
        if(this.length() != b.length()){
            System.err.println("Error during and operation: bits length doesn't match");
            return null;
        }
        Bits temp = new Bits(this.length());
        for(int i = 0; i < this.length(); i++)
            temp.set(i, this.get(i) || b.get(i));
        return temp;
    }




    public void setBits(boolean[] b){
        this.bits = b;
    }

    public int length(){
        return this.bits.length;
    }

    public boolean[] getBits(){
        return this.bits;
    }

    public boolean get(int i){
        return this.bits[i];
    }
    public void set(int i){
        this.bits[i] = true;
    }
    public void set(int i, boolean val){
        this.bits[i] = val;
    }

    public String toString(){
        String toString = "";
        for(int i = 0; i < this.bits.length; i++)
            toString += this.bits[i] ? 1:0;
        return toString;
    }
}
