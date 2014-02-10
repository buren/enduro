# Skapen en target mapp med version inläst från prompt
# kopiera manual.md till target
# flytta jar till target
# kopiera acceptanstester till target
# zip target
read -p "What version is this?" version_number


release_name="enduro_release_v$version_number"

target_dir=release/release_v$version_number/
mkdir -p $target_dir
cp Manual.md $target_dir
cp StoryList.md $target_dir
cp target/*.jar $target_dir
rm $target_dir/*with-dependencies.jar
cp -r src/test/resources/acceptanstester/ $target_dir
zip -r $release_name $target_dir
mv $release_name.zip $HOME/Desktop
echo -e "Release name will be: '$release_name' and will be placed on your Desktop folder when done"