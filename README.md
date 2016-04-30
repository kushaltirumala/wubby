# wubby
java based webcam


go [here](http://docs.opencv.org/3.0-beta/doc/tutorials/introduction/java_eclipse/java_eclipse.html) to download + configure the plugin for eclipse

if that doesn't work run the following in shell:

<code>cd ~</code> 


<code>mkdir Vision</code>


<code>cd Vision</code>


<code>git clone git://github.com/Itseez/opencv.git</code>
that will take some time


<code>cd opencv</code>


<code>mkdir build</code>


<code>cd build</code>


<code>cmake -DBUILD_SHARED_LIBS=OFF ..</code>

(if "cmake is not found", download cmake by running <code>brew install cmake</code>)


<code>make -j8</code>
this is also gonna take some time


then go into eclipse, open preferences, add a new library, select it and add the new external opencv.jar as a library within your project and you're good to go :)


