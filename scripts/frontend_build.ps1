$scriptPath = split-path -parent $MyInvocation.MyCommand.Definition
cd $scriptPath
cd ..
cd CraftingFrontend
npm install
npm run ng build