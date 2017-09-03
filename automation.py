import argparse
import os
import shutil
import subprocess
import time


deploy_dir_base = "target"
resources_dir_base = "resources"


class Artifact:
    def __init__(self, artifact_name, instance_name):
        self.deploy_dir = deploy_dir_base + "\\" + instance_name
        self.sources_dir = artifact_name + "\\" + deploy_dir_base
        self.resources_dir = resources_dir_base + "\\" + instance_name
        self.jar_name = artifact_name + "-0.0.1-SNAPSHOT.jar"

    def create_deploy_dir(self):
        os.mkdir(self.deploy_dir)

    def copy_jar_file(self):
        shutil.copytree(self.sources_dir + "\\lib", self.deploy_dir + "\\lib")
        shutil.copy2(self.sources_dir + "\\" + self.jar_name, self.deploy_dir)

    def copy_configuration_file(self):
        shutil.copy2(self.resources_dir + "\\configuration.xml", self.deploy_dir)

    def launch(self, base_dir):
        os.chdir(base_dir + "\\" + self.deploy_dir)
        subprocess.Popen("java -jar " + self.jar_name, shell=True, close_fds=True)


class Automation:
    def __init__(self):
        self.artifacts = [
            Artifact("factory-container", "factory-container"),
            Artifact("lego-container", "lego-container-1"),
            Artifact("lego-container", "lego-container-2"),
            Artifact("remote-container", "remote-container"),
        ]

    def clean(self):
        print("deleting all artifacts from previous deployment ...")
        if os.path.exists(deploy_dir_base):
            shutil.rmtree(deploy_dir_base)

    def create_folders(self):
        print("creating deployment structure ...")
        os.mkdir(deploy_dir_base)
        for artifact in self.artifacts:
            artifact.create_deploy_dir()

    def copy_jar_files(self):
        print("deploying jar files ...")
        for artifact in self.artifacts:
            artifact.copy_jar_file()

    def copy_configuration_files(self):
        print("deploying configuration files ...")
        for artifact in self.artifacts:
            artifact.copy_configuration_file()

    def launch_jar_files(self):
        print("launching ...")
        current_dir = os.getcwd()
        for artifact in self.artifacts:
            artifact.launch(current_dir)
            time.sleep(2)


class ApplicationAction:
    def __init__(self, action_name, action_callback):
        self.action_name = action_name
        self.action_callback = action_callback

    def execute(self):
        self.action_callback()
        print("done")


class Application:
    def __init__(self):
        self.automation = Automation()
        self.application_actions = [
            ApplicationAction("deploy-all", self.action_deploy_all),
            ApplicationAction("deploy-configuration", self.action_deploy_configuration),
            ApplicationAction("deploy-jar", self.action_deploy_jar),
            ApplicationAction("clean-all", self.action_clean_all),
            ApplicationAction("launch-all", self.action_launch_all)
        ]

    def run(self):
        choices = []
        for application_action in self.application_actions:
            choices.append(application_action.action_name)

        parser = argparse.ArgumentParser()
        parser.add_argument("action", help="specify action to perform", choices=choices)

        args = parser.parse_args()

        is_application_action_found = False
        for application_action in self.application_actions:
            if args.action == application_action.action_name:
                application_action.action_callback()
                is_application_action_found = True
                break

        if not is_application_action_found:
            parser.print_usage()

    def action_deploy_all(self):
        self.automation.clean()
        self.automation.create_folders()
        self.automation.copy_jar_files()
        self.automation.copy_configuration_files()
        print("done")

    def action_clean_all(self):
        self.automation.clean()
        print("done")

    def action_deploy_configuration(self):
        self.automation.copy_configuration_files()
        print("done")

    def action_deploy_jar(self):
        self.automation.copy_jar_files()
        print("done")

    def action_launch_all(self):
        self.automation.launch_jar_files()
        print("done")


if __name__ == '__main__':
    Application().run()
