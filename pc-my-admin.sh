#!/bin/bash
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin
export PATH
ulimit -n 8192

PLATFORM_HOME=/data/web/pc-my-admin

jvmArgs=" -server -Xms512m -Xmx1024m  -Xss512k -XX:PermSize=128M -XX:MaxNewSize=64m -XX:MaxPermSize=128m -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "

CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/jre/lib
LANG=zh_CN.GB18030
export PLATFORM_HOME CLASSPATH LANG

Search_Str="pc-my-admin"

LOG=$PLATFORM_HOME/logs/pc-my-admin.log

case $1 in
start)
	cd $PLATFORM_HOME/
	nohup $JAVA_HOME/bin/java  -Djava.ext.dirs=$PLATFORM_HOME/lib:$JAVA_HOME/jre/lib/ext   $jvmArgs   -jar pc-my-admin-3.0-SNAPSHOT.jar >$LOG 2>&1 &
	if [ $? -ne 0 ];then
		echo "$0 start failed"
	else
		echo "$0 start success"
	fi
	chmod 644 $LOG
	sleep 2
	$0 chk
;;
stop)
	cd $PLATFORM_HOME/
	PID=`ps auxwww|grep java|grep $Search_Str|awk '{ print $2 }'`
	if [ -z "$PID" ]; then
		echo "no pid found. $0 no start?"
	else
		kill -9 $PID && echo "kill pid $PID success." || echo "kill pid $PID failed."
	fi
	[[ -f $LOG ]] && mv -vf $LOG $LOG.1
;;
forcestop|fstop)
	PID=`ps auxwww|grep java|grep $Search_Str|awk '{ print $2 }'`
	if [ -z "$PID" ]; then
		echo "no pid found. $0 no start?"
	else
		kill -9 $PID && echo "kill pid $PID success." || echo "kill pid $PID failed."
	fi
;;
check|chk)
	PID=`ps auxwww|grep java|grep $Search_Str|awk '{ print $2 }'`
	if [ -z "$PID" ]; then
        	echo -n "no pid found. $0 no start.make it start?(y/n) "
		read Confirm
		if [ "$Confirm" = "y" ];then
			$0 start
		else
			echo "You said no start"
		fi
	else
        	echo "pid $PID found. $0 started"
	fi
;;
restart)
	$0 stop
	sleep 2
	$0 start
	cd $PLATFORM_HOME/
	sleep 2
	tail -2 $LOG
	$0 chk
;;
autochk)
	PID=`ps auxwww|grep java|grep $Search_Str|awk '{ print $2 }'`
	if [ -z "$PID" ]; then
		echo "no pid found. $0 auto start"
		$0 start
        else
                echo "find pid $PID."
	fi
;;
readlog)
	[[ -z "$2" ]] && tLine=20 || tLine=$2
	cd $PLATFORM_HOME/
	tail -n $tLine $LOG
;;
*)
	echo "Usage: $0 [start|stop|restart|chk|autochk|readlog <lines>|forcestop]"
	exit 0
esac







