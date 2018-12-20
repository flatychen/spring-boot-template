#! /bin/sh
name="litemall"
jarName=`ls | grep -e "^\${name}.*jar$" | sort -r | head -n 1`
chmod +x ${jarName}
echo `pwd`/${jarName}
ln -f -s `pwd`/${jarName}  /etc/init.d/${name}
service litemall restart