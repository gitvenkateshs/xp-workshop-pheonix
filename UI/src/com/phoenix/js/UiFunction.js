
	var video;
	var v_source;
	var array_url;
	var array_index;
	function setup(){
		video = document.getElementById("displayer");
		v_source = document.getElementById("video_source");
		array_url=["../../../../media/Serial Experiments Lain - 01 [By BahZell].mp4","../../../../media/Serial Experiments Lain - 02 [By BahZell].mp4","../../../../media/Serial Experiments Lain - 03 [By BahZell].mp4"];
		array_index=0;
	}	

	function prog_next() {
		video = document.getElementById("displayer");
		v_source = document.getElementById("video_source");
		if (array_index<2)	{array_index+=1;}
		v_source.setAttribute("src",array_url[array_index]);
		video.load();
		video.play();
		console.log(array_index);
    }
	function prog_down() {
		video = document.getElementById("displayer");
		v_source = document.getElementById("video_source");
		if (array_index>0)	{array_index-=1;}
		v_source.setAttribute("src",array_url[array_index]);
		video.load();
		video.play();
		console.log(array_index);
    }
	
	function vol_down(){
		video.volume -=0.25;
		if (v_source.volume>0){
		v_source.volume -=0.25;}
	}
	function vol_up(){
		video.volume +=0.25;
		if (v_source.volume<1){
		v_source.volume +=0.25;}
	}

	function power_off(){
		var power_tog = document.getElementById("main_div");
		var power_text = document.getElementById("power");
		power_text.value = "ON";
		video.pause();
//		video.setAttribute(display,"none");
		location.href='off.html';
		
	}
    function restart() {
        var video = document.getElementById("displayer");
        video.currentTime = 0;
    }

    function skip(value) {
        var video = document.getElementById("displayer");
        video.currentTime += value;
    }      