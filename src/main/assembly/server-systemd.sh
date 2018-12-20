name="litemall"

dir=`pwd`
jarname=`ls | grep -e "^\${name}.*jar$" | sort -r | head -n 1`
dir=`echo ${dir//\//\\\/}`
sed -i "s/^ExecStart.*/ExecStart=${dir}${jarname}/g" /etc/systemd/system/${name}.service
systemctrl restart ${name}.service
systemctrl status ${name}.service
