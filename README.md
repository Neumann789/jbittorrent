# jbittorrent


## Description 

jbittorrent is a simple and easy to use Java implementation of the BitTorrent protocol. It provides  a set of classes that allow the creation of the .torrent files and the download and sharing of files between peers in a BitTorrent swarm. 


The project is based on the library Java BitTorrent API developed by Baptiste Dubuis, Artificial Intelligence Laboratory, EPFL.  Java BitTorrent API is a free software that can be redistributed or modified under the terms of the GNU General Public License as published by the Free Software Foundation. To get more information about the Java BitTorrent API: http://sourceforge.net/projects/bitext/


##### jbittorrent improves and extends the previously described library with the following features:

* Optimisation of the management mechanism that maintains the connections between the peers of the swarms. The new version of the library solves the multiple connection problem in the version 1.0 of the Java Bittorrent API. This problem was due to the fact that connections are identified by the IP and the port of the peer in question which might result in multiple connections for one peer. 

* The improvement of the implementation of the Choking Algorithm guaranteeing that the number of unchoked peers is exactly five

* New implementation of the Optimistic Unchoking process that makes it easier for a peer to get its first pieces.

* Implementation of the Rarest First Algorithm in the process of the pieces selection process. 

* Implementation of the End Game Strategy. 


## Principal Classes 

### DownloadManager: 
manages the connections and the simultaneous downloads of the peer with the rest of the peers of the swarm. It is also in charge of the peers and pieces selection processes.


#### Constructors and parameters:

        public DownloadManager(TorrentFile torrent, final byte[] clientID, String savePath)
        
- **torrent:** the information about the .torrent file to be downloaded and/or shared.
-	**clientID:** the peer identifier with which it will announced to the tracker.
-	**savePath:** the path location where will be saved the downloaded and/or shared file.
        
        

#### Optional parameters:

        public DownloadManager(TorrentFile torrent, final byte[] clientID, String savePath, int intervalUpdateListPeers, int blockSize, float thresholdEndGameTest)

-	**intervalUpdateListPeers:** establishes the periodicity, in seconds, with which the peer is announced in the tracker; in accordance with the tracker protocol HTTP/HTTPS and the announceURL specified in the TorrentFile.  The connections of  the peers with which it interacts, will be revised, and new connections will be established, if the peers list returned by the tracker, contains new peers.
-	**blockSize:** determines the size, in KB, of the blocks.  The default value is 16 kB.
-	**thresholdEndGameTest:** determines when to activate the EndGame strategy. If this value is set to -1 (which is the default value), the EndGame mode  will be activated according to the original protocol defined by Bram Cohen (i.e. when all the pieces have been solicited). It is also possible to activate this mode when a certain predefined percentage of the download is achieved (for example, after receiving 90% of the total size of the file).




### DownloadTask: 
represents a download task. It is linked to one peer and its connection.   This class manages the exchange of messages that define BitTorrent protocol (choke, unchoke, interested, not interested, have, bitfield, … etc.).


### Peer Updater: 
provides a series of methods to permit the communication between the peer and a tracker.  These methods decode and analyse the tracker response.


### TorrentProcessor: 
allows the creation and the processing of a .torrent file.


## Instruction about how to compile and execute the jbittorrent package

To compile and execute the jbittorrent library, you need to follow the steps mentioned in this section.

The package trackerBT is the same as the one of the Java Bittorrent API Project. This package aims at providing users with a simple and easy-to-use Bittorrent tracker that can be easily configurable and usable. To find out more about this package, please refer to the README_tracketBT.txt file which contains all the information about its compilation and execution steps.

### Requirements

To run this project, you need the Java SE Development Kit 6 Update 45 or superior that can be found here:
http://www.oracle.com/technetwork/es/java/javase/downloads/index.html

### Compilation

There are several ways to compile the files:

-  Using  java command from the jbittorrent directory. For example, from a console using: 

        javac -sourcepath src -classpath build;ext\log4j-1.2.17.jar src\jbittorrent\*.java src\test\*.java -d build

-	Using the script build.bat for MS-Windows systems and build.sh  for UNIX systems.

-	You can compile and create a .jar file, using the buil.xml file with the Apache Ant command-line tool. You can download Apache Ant: http://ant.apache.org/bindownload.cgi

### Execution of the examples

The package comes along with 4 simple example classes that implement each a distinct part of the protocol:

-	**ExampleCreateTorrent:** create a new torrent file

-	**ExamplePublish:** publishes a torrent file the online tracker smartorrent.com (http://www.smartorrent.com/) on on the trackerBT which is already provided with jbittorrent package.

-	**ExampleDownloadFiles and ExampleTestClientTorrent:** used to download and upload files described in a torrent

 

To execute these classes, you need first to compile the .java files, as explained in the previous paragraph. In the case where the Ant tool is used, it is required to change the following line depending on the targeted class:

        <project name="jbittorrent" basedir="." default="ExampleDownloadFiles">

The default tag will be the name of one of this three examples that you want to execute, ExampleCreateTorrent, ExampleDownloadFiles and ExampleTestClientTorrent.

After that, it is only left to execute the command ant in a console (from the jbittorrent directory). A jar file named jbitorrent.jar will be created under directory/dist.


#### ExampleCreateTorrent


To execute this example, it is required to define some input parameters. The command to launch the execution has the following format (to be called from the jbittorrent directory):

        java -classpath build;ext\log4j-1.2.17.jar test.ExampleCreateTorrent <torrentPath> <announceURL> <pieceLength> <filePath1> … <filePathN> <…> <creator> <…> <comment>

Or if the Ant tool is used,  the command will have this format (to be called from the jbittorrent/dist directory):

        java -jar jbittorrent.jar <torrentPath> <announceURL> <pieceLength> <filePath1> … <filePathN> <…> <creator> <…> <comment>



##### Information about the parameters:


-	**torrentPath:**  the path and name of the file the torrent will be saved as. This path must end with .torrent extension and it must contain no spaces.
-	**announceURL:** the announce url of the tracker twhere the torrent is to be published. This url must also contain no spaces.
-	**pieceLength:**  length of the pieces the target files will be split into,expressed in kB.This parameter has to be an integer. The default value is 256 (pieces of 256kB length each) .
-	**filePathX:**  the path and name of the Xth file to be inserted in the torrent. If there is more than one such parameter, the firest one must only be the name of the directory the target files will be saved into when a client downloads them.
-	**<…>:**  represent the string “..” and is used to separate parameters.
-	**creator:**  the name of the creator of the torrent. This parameter is recommended as it represents the name and version of the program that created it.
-	**comment:** any desired comment about the torrent to be inserted.

 

#### ExamplePublish


The command to execute this class is (from the jbittorrent directory):

        java -classpath  build;ext\log4j-1.2.17.jar test.ExamplePublish <torrentPath> <trackerURL> <username> <password> <comment>

Or, with the Ant tool, the command is (from the jbittorrent/dist directory):

        java -jar jbittorrent.jar <torrentPath> <trackerURL> <username> <password> <comment>



##### Information about the parameters:


-	**torrentPath:** the path and name of the torrent the user wants to publish..
-	**trackerURL:** the url of the tracker accepting torrent upload. Note here that for the moment, publishing is only possible on smartorrent tracker or on a host running the trackerBT tracker.
-	**username:** the username registered on the tracker
-	**password:** the password registered on the tracker
-	**comment:** comment about the torrent, it will serve as a description for people searching for torrents.



#### ExampleDownloadFiles 


To be executed, this class needs only the torrent path and the directory where will be saved the downloaded file. Therefore, the command to launch the execution is (from the jbittorrent directory):
 
        java -classpath  build;ext\log4j-1.2.17.jar test.ExampleDownloadFiles <torrentPath> <savingDirectory>

Or, with the Ant tool, the command to launch the execution is (from the jbittorrent/dist directory):

        java -jar jbittorrent.jar <torrentPath> <savingDirectory>

 
 
#### ExampleTestClientTorrent


As the previous class, this class needs also the torrent path and the directory where will be saved the downloaded file along with some other parameters. The command to launch the execution is (from the jbittorrent directory):   

        java -classpath  build;ext\log4j-1.2.17.jar test.ExampleTestClientTorrent <torrentPath> <savingDirectory> <blockSize> <intervalUpdateLiestPeers> <thresholdEndGameTest>

Or, with the Ant tool, the command  is (from the jbittorrent/dist directory):

        java -jar jbittorrent.jar <torrentPath> <savingDirectory> <blockSize> <intervalUpdateLiestPeers> <thresholdEndGameTest>



##### Information about the parameters:


-	**blockSize:** is the size, in kB, of the blocks in which pieces will be divided during their transfer. The default value in the BitTorrent protocol is 16 kB, but any other value can be given here.
  


##### Information about the optional parameters


-	**intervalUpdateListPeers:** defines the periodicity, in seconds, with which the peer makes an announce in the tracker and new connections are established between peers (By default 170 sec.)
-	**thresholdEndGameTest:** determines when to activate the EndGame strategy. If this value is set to -1 (which is the default value), the EndGame mode  will be activated according to the original protocol defined by Bram Cohen (i.e. when all the pieces have been solicited). It is also possible to activate this mode when a certain predefined percentage of the download is achieved (for example, after receiving 90% of the total size of the file).


## The project’s content

- **README:** information about this release and further instruction about how to compile and execute the jbittorrent package.
-	**README-example:** further instruction about how to run the examples provided.
-	**README-trackerBT:** further instruction about how to compile and run the trackerBT package.
-	**build.bat:** compile script for MS-Windows systems, (for jbittorrent and test packages).
-	**build.sh:** compile script for UNIX systems, (for jbittorrent and test packages).
-	**buildTrackerBT.bat:** compile script for MS-Windows systems, (for trackerBT packages).
-	**buildTrackerBT.sh:** compile script for UNIX systems, (for trackerBT package).
-	**build.xml:** compile y compress the class jbittorrent and test package in jbittorrent.jar
-	**dist/jbittorrent.jar:** compressed classes of the jbittorrent package
-	**dist/trackerBT.jar:** compressed classes of the trackerBT package
-	**documentation/:** javadoc of the jbittorrent and trackerBT packages
-	**example/:** files and directory needed to run the provided example step by step
-	**ext/:** external libraries .jar files
-	**src/:** source files for jbittorrent, test and trackerBT packages
-	**LICENSE:** the license of this project
-	**GNU_GPL:** the GNU General Public License
-	**env.bat:** environment configuration script for MS-Windows systems
-	**env.sh:** environment configuration script for UNIX systems
