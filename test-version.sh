
PROPERTIES="$(./gradlew properties --console=plain -q)"
VERSION="$(echo "$PROPERTIES" | grep "^version:" | cut -f2- -d ' ')"
CHANGELOG="$(./gradlew getChangelog --unreleased --no-header --console=plain -q)"

echo "version=$VERSION"
echo "pluginVerifierHomeDir=~/.pluginVerifier"

echo "changelog<<EOF"
echo "$CHANGELOG"
echo "EOF"

./gradlew listProductsReleases # prepare list of IDEs for Plugin Verifier
