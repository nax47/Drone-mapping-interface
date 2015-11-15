var fs = require('fs');

function read(callback){
    var fil = fs.readFile("commands.txt",function(err,data){
        var array = data.toString().split("\n");
        fs.writeFileSync("flight_path.js","var fs = require('fs');\nvar drone = require('ar-drone');\nvar client = drone.createClient();\nvar pngStream = client.getPngStream(); \
        \nvar pic_counter = 0;\n\nclient.config('control:flight_without_shell', 0, function(err){console.log('Drone using indoor hull');}); \
        \nclient.config('control:outdoor', 0, function(err){console.log('Drone set to indoor mode');});\
        \nclient.config('control:altitude_max', 100000, function(err){console.log('Altitude limit set to 100 m.');}); \
        \n\nclient.takeoff(function(err){console.log('Takeoff complete');});\nconsole.log('Taking off');\n\nclient.after(5000,function(){ \
        \nthis.up(0.8);\nconsole.log('Moving up');\n})\n.after(4000,function(){\nthis.stop();\nconsole.log('Stopping');\n");
        callback(array);
    });
}

function parse(data){
    data.pop();
    for(i in data){
        var cords = data[i].split(" ");
        for(i in cords){
            //console.log(cords[i]);
            var val = "";
            if(cords[i].charAt(0)=="U"){
                fs.appendFileSync("flight_path.js","this.up(0.8);\nconsole.log('Moving up');\n})\n.after(4000,function(){ \
                    \nthis.stop();\nconsole.log('Stopping');\n");
            }
            if(cords[i].charAt(0)=="D"){
                fs.appendFileSync("flight_path.js","this.down(0.8);\nconsole.log('Moving down');\n})\n.after(4000,function(){ \
                    \nthis.stop();\nconsole.log('Stopping');\n");
            }
            if(cords[i].charAt(0)=="T"){
                fs.appendFileSync("flight_path.js","pngStream.once('data', function(data){\nvar file_name = 'dronepic'+ pic_counter++ +'.png'; \
                \nfs.writeFile(file_name, data, function(err){\nif(err) console.log(err);\nconsole.log(file_name+' saved!');\n});\n});\n}) \
                \n.after(1000,function(){\nthis.stop();\nconsole.log('Stopping');\n");
            }
            if(cords[i].charAt(0)=="X"){
                if(cords[i].charAt(1)=="-"){
                    for(var j=2; j<cords[i].length; j++){
                        val+=(cords[i].charAt(j));
                    }
                    val = parseInt(val)/20;
                    fs.appendFileSync("flight_path.js","this.back(0.1);\nconsole.log('Moving backward');\n})\n.after("+(val*2000)+",function(){ \
                    \nthis.stop();\nconsole.log('Stopping');\n");
                }
                else{
                    for(var j=1; j<cords[i].length; j++){
                        val+=(cords[i].charAt(j));
                    }
                    val = parseInt(val)/20;
                    fs.appendFileSync("flight_path.js","this.front(0.1);\nconsole.log('Moving forward');\n})\n.after("+(val*2000)+",function(){ \
                    \nthis.stop();\nconsole.log('Stopping');\n");
                }
            }
            else if(cords[i].charAt(0)=="Y"){
                if(cords[i].charAt(1)=="-"){
                    for(var j=2; j<cords[i].length; j++){
                        val+=(cords[i].charAt(j));
                    }
                    val = parseInt(val)/20;
                    fs.appendFileSync("flight_path.js","this.left(0.1);\nconsole.log('Moving left');\n})\n.after("+(val*2000)+",function(){ \
                    \nthis.stop();\nconsole.log('Stopping');\n");
                }
                else{
                    for(var j=1; j<cords[i].length; j++){
                        val+=(cords[i].charAt(j));
                    }
                    val = parseInt(val)/20;
                    fs.appendFileSync("flight_path.js","this.right(0.1);\nconsole.log('Moving right');\n})\n.after("+(val*2000)+",function(){ \
                    \nthis.stop();\nconsole.log('Stopping');\n");
                }
            }
        }
    }
    fs.appendFileSync("flight_path.js","this.land(function(err){console.log('Landing complete');});\nconsole.log('Landing');\n});\n");
}

read(function(data){
    parse(data);
});
