# wubby

run the following in shell:

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


then go into eclipse, open preferences, add a new library (name it something like OpenCV-3.1.0), select it and add a new external jar which should be under <code>/Vision/opencv/build/bin</code>. Then, on the dropdown of options, click "native library" and on the side bar click edit. Browse to find the lib folder (which should be under <code>/Vision/opencv/build/lib</code>). 

Now create a new java project, and after choosing the name click "next" NOT "finish" on the bottom, select the tab "libraries", click add a library, navigate to user libraries, and select OpenCV-3.1.0. That's it, you're done :)

-rtang xd


