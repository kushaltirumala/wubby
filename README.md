# wubby
java based webcam


go [here](http://docs.opencv.org/3.0-beta/doc/tutorials/introduction/java_eclipse/java_eclipse.html) to download + configure the plugin for eclipse

if that doesn't work run the following in shell:

cd ~
mkdir Vision
cd Vision
git clone git://github.com/Itseez/opencv.git
cd opencv
mkdir build
cd build
cmake -DBUILD_SHARED_LIBS=OFF ..
make -j8

then go into eclipse, open preferences, add a new library, select it and add the new external opencv.jar as a library within your project and you're good to go :)


