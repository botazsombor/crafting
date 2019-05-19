$scriptPath = split-path -parent $MyInvocation.MyCommand.Definition
cd $scriptPath
cd ..
cd backend
mvn test