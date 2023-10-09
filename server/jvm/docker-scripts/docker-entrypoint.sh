#!/bin/bash
systemctl start postgresql-14
systemctl enable sshd.service
systemctl start sshd.service
su -c "startServer" - "athene"
echo "Logged as athene, starting server"
tail -f /dev/null