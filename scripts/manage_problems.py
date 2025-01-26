import os
import requests
import json
from collections import defaultdict

SOLVED_AC_API_URL = "https://solved.ac/api/v3/problem/show"
GH_API_BASED_URL = "https://api.github.com"
GH_API_BASED_HEADERS = {
    "Accept": "application/vnd.github+json"
}

def get_problem_info(problem_id):
    response = requests.get(f"{SOLVED_AC_API_URL}?problemId={problem_id}")
    if response.status_code != 200:
        raise Exception(f"Failed to fetch problem info for problem ID {problem_id}: {response.status_code}")

    data = response.json()
    levels = {
        0: "Unrated",
        1: "Bronze 5", 2: "Bronze 4", 3: "Bronze 3", 4: "Bronze 2", 5: "Bronze 1",
        6: "Silver 5", 7: "Silver 4", 8: "Silver 3", 9: "Silver 2", 10: "Silver 1",
        11: "Gold 5", 12: "Gold 4", 13: "Gold 3", 14: "Gold 2", 15: "Gold 1",
        16: "Platinum 5", 17: "Platinum 4", 18: "Platinum 3", 19: "Platinum 2", 20: "Platinum 1"
    }

    return {
        "problem_id": data["problemId"],
        "problem_name": data["titleKo"],
        "problem_level": levels[data["level"]],
        "problem_tags": [tag["key"] for tag in data["tags"]],
        "problem_link": f"https://www.acmicpc.net/problem/{data['problemId']}"
    }

def get_latest_commit_file_path(owner, repo, gh_token):
    headers = {
        "Authorization": f"Bearer {gh_token}",
        "Accept": "application/vnd.github+json"
    }
    commits_url = f"{GH_API_BASED_URL}/repos/{owner}/{repo}/commits"

    try:
        # Get the latest commit SHA
        response = requests.get(commits_url, headers=headers, params={"per_page": 1, "page": 1})
        response.raise_for_status()
        latest_commit = response.json()[0]
        commit_sha = latest_commit["sha"]

        commit_detail_url = f"{GH_API_BASED_URL}/repos/{owner}/{repo}/commits/{commit_sha}"
        detail_response = requests.get(commit_detail_url, headers=headers)
        detail_response.raise_for_status()
        commit_data = detail_response.json()

        if "files" in commit_data and len(commit_data["files"]) > 0:
            return commit_data["files"][0]["filename"]
        else:
            print(f"Warning: No files found in the latest commit. Commit message: {latest_commit['commit']['message']}")
            return None
    except requests.exceptions.RequestException as e:
        print(f"Failed to fetch latest commit file path: {e}")
        return None


from collections import defaultdict

def generate_readme(base_dir, problems):
    categorized_problems = defaultdict(list)
    for problem in problems:
        main_tag = problem["problem_tags"][0] if problem["problem_tags"] else "Uncategorized"
        categorized_problems[main_tag].append(problem)

    readme_content = [
        '<p align="center"> <a href="https://solved.ac/gs030216"> <img src="http://mazassumnida.wtf/api/generate_badge?boj=gs030216" alt="Solved.ac Profile"> </a> </p>\n\n',
        "# Baekjoon Problem Solving\n"
    ]

    for tag, problem_list in sorted(categorized_problems.items(), key=lambda x: x[0]):
        readme_content.append(f"<details>\n<summary><b>{tag}</b></summary>\n\n")
        readme_content.append("| Problem ID | Title | Level | Problem Link | Code |\n")
        readme_content.append("|------------|-------|-------|--------------|------|\n")
        for problem in problem_list:
            file_path = problem.get("file_path", "N/A")
            problem_link = problem["problem_link"]

            readme_content.append(
                f"| {problem['problem_id']} | {problem['problem_name']} | {problem['problem_level']} | "
                f"[Problem Link]({problem_link}) | [Code]({file_path}) |\n"
            )
        readme_content.append("\n</details>\n\n")

    with open("README.md", "w", encoding="utf-8") as f:
        f.writelines(readme_content)


def main():
    commit_message = os.getenv("COMMIT_MESSAGE")
    gh_token = os.getenv("GH_TOKEN")
    owner = os.getenv("OWNER")
    repo = os.getenv("REPO")

    if not all([gh_token, owner, repo]):
        raise Exception("Missing required environment variables: GH_TOKEN, OWNER, REPO")

    if not commit_message or not commit_message.startswith("feat : solve"):
        print(f"Invalid or missing commit message: {commit_message}")
        return

    try:
        problem_id = commit_message.split(" ")[-1]
    except IndexError:
        raise Exception("Failed to extract problem ID from commit message.")

    problem_info = get_problem_info(problem_id)

    file_path = get_latest_commit_file_path(owner, repo, gh_token)
    if not file_path:
        print("No file path detected. Skipping file-specific processing.")
        return

    problem_info["file_path"] = file_path

    problems_file = "problems.json"
    if os.path.exists(problems_file):
        with open(problems_file, "r", encoding="utf-8") as f:
            problems = json.load(f)
    else:
        problems = []

    problems.append(problem_info)

    with open(problems_file, "w", encoding="utf-8") as f:
        json.dump(problems, f, indent=4)

    generate_readme("src", problems)

if __name__ == "__main__":
    main()
