name="litemall"

dir=`pwd`
jarname=`ls | grep -e "^\${name}.*jar$" | sort -r | head -n 1`
chmod +x ${jarName}
dir=`echo ${dir//\//\\\/}`
sed -i "s/^ExecStart.*/ExecStart=${dir}\/${jarname}/g" /etc/systemd/system/${name}.service
systemctl daemon-reload
systemctl restart ${name}.service
systemctl status ${name}.service
