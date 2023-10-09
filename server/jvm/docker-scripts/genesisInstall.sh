#!/bin/bash
source /home/athene/.bashrc
systemctl start postgresql-14
su -c "source /home/athene/.bashrc ; genesisInstall" - "athene"
echo "genesisInstall done"