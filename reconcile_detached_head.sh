git status
#-- detached head

git branch temp
git checkout temp

git log --graph --decorate --pretty=oneline --abbrev-commit master origin/master temp
git diff master temp
git diff origin/master temp

@see http://stackoverflow.com/questions/5772192/git-how-can-i-reconcile-detached-head-with-master-origin


#If your new temp branch looks good, you may want to update (e.g.) master to point to it:

git branch -f master temp
git checkout master

