#!/bin/sh
# Skapen en target mapp med version inläst från prompt
# kopiera manual.md till target
# flytta jar till target
# kopiera acceptanstester till target
# zip target

set -e # Exit on failed command

read -p "What version is this?  " version_number

release_name="enduro_release_v$version_number"
current_dir=$(pwd)
target_dir=release/release_v$version_number/
$HOME/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn package
$HOME/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn javadoc:javadoc
for i in docs/*.md; do
  perl docs/markdown_to_html/Markdown.pl --html4tags $i > ${i%.*}.html
done;
mkdir -p $target_dir
cp docs/*.html $target_dir
cp docs/*.png $target_dir
cp target/*.jar $target_dir
mkdir $target_dir/javadoc
cp -r target/site/apidocs/* $target_dir/javadoc
rm $target_dir/*with-dependencies.jar
cp -r src/test/resources/acceptanstester/ $target_dir
zip -r $release_name $target_dir
mv $release_name.zip $HOME/Desktop
echo -e "Release name will be: '$release_name' and will be placed on your Desktop folder when done"

